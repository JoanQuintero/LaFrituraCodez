package com.lafrituracodez.letsdeal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;


public class LoginActivity extends Activity implements View.OnClickListener {

	protected final static int RC_SUCCESS_SIGN_IN = 23;
	protected final static String TAG = "LoginActivity";

	private FirebaseAuth mAuth;
	private GoogleSignInClient googleSignInClient;

	@Override
	protected void onStart() {
		super.onStart();
		FirebaseUser currentUser = mAuth.getCurrentUser();
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
				.requestIdToken(getString(R.string.client_id))
				.requestEmail()
				.build();
		googleSignInClient = GoogleSignIn.getClient(this, gso);

		findViewById(R.id.sign_in_button).setOnClickListener(this);
		mAuth = FirebaseAuth.getInstance();

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.sign_in_button) {
			signIn();
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == RC_SUCCESS_SIGN_IN) {
			Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
			try {
				// Google Sign In was successful, authenticate with Firebase
				GoogleSignInAccount account = task.getResult(ApiException.class);
				firebaseAuthWithGoogle(account);
			} catch (ApiException e) {
				Log.w(TAG, "Google sign in failed", e);
			}
		}
	}

	private void firebaseAuthWithGoogle(final GoogleSignInAccount acct) {
		Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

		AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
		mAuth.signInWithCredential(credential)
				.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {
						if (task.isSuccessful()) {
							// Sign in success, update UI with the signed-in user's information
							Log.d(TAG, "signInWithCredential:success");
							FirebaseUser user = mAuth.getCurrentUser();
							updateUI(acct);
						} else {
							// If sign in fails, display a message to the user.
							Log.w(TAG, "signInWithCredential:failure", task.getException());
							Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
							updateUI(null);
						}

						// ...
					}
				});
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
		if (GoogleSignIn.getLastSignedInAccount(this) != null) {
			Intent intent = new Intent();
			setResult(RC_SUCCESS_SIGN_IN, intent);
			finish();
		}
	}

	@Override
	public void onBackPressed() {
		Intent a = new Intent(Intent.ACTION_MAIN);
		a.addCategory(Intent.CATEGORY_HOME);
		a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(a);
	}

	private void updateUI(GoogleSignInAccount account) {
		if (account != null) {
			AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
			FirebaseAuth.getInstance().signInWithCredential(credential);
			startApp();
		}
	}

}
