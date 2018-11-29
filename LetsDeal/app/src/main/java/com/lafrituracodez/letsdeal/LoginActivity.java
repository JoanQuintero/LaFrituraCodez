package com.lafrituracodez.letsdeal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;


public class LoginActivity extends Activity implements View.OnClickListener {
	private final static int RC_SUCCESS_SIGN_IN = 23;

	private GoogleSignInClient googleSignInClient;

	@Override
	protected void onStart() {
		super.onStart();
		GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
		if (account != null) {
			startApp();
		}
		updateUI(account);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
				.requestEmail()
				.build();
		googleSignInClient = GoogleSignIn.getClient(this, gso);

		findViewById(R.id.sign_in_button).setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.sign_in_button:
				signIn();
				break;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		// Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
		if (requestCode == RC_SUCCESS_SIGN_IN) {
			// The Task returned from this call is always completed, no need to attach
			// a listener.
			Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
			handleSignInResult(task);
			startApp(); //I have a feeling this doesn't belong here 
			// CleanUpCrew please check this.

		}
	}

	private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
		try {
			GoogleSignInAccount account = completedTask.getResult(ApiException.class);
			updateUI(account);
		} catch (ApiException e) {
			Log.w("Google Account", "signInResult:failed code=" + e.getStatusCode());
			updateUI(null);
		}
	}

	private void signIn() {
		Intent signInIntent = googleSignInClient.getSignInIntent();
		startActivityForResult(signInIntent, RC_SUCCESS_SIGN_IN);
	}

	private void startApp() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	private void updateUI(GoogleSignInAccount account) {
		if (account != null) {
			startApp();
			Toast.makeText(this, "Welcome " + account.getDisplayName(), Toast.LENGTH_SHORT).show();
		}
	}

}
