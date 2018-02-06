package com.example.layout.assig16_2;
//Package objects contain version information about the implementation and specification of a Java package
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    //public keyword is used in the declaration of a class,method or field;public classes,method and fields can be accessed by the members of any class.
//extends is for extending a class. implements is for implementing an interface
//AppCompatActivity is a class from e v7 appcompat library. This is a compatibility library that back ports some features of recent versions of
// Android to older devices.
    public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {
        //AsyncTask is designed to be a helper class around Thread and Handler and does not constitute a generic threading framework
       //A user interface element that indicates the progress of an operation.
        ProgressBar myProgressBar;

        public MyAsyncTask(ProgressBar target) {
            myProgressBar = target;
        }

        @Override
        protected Void doInBackground(Void... params) {
            //A user interface element that indicates the progress of an operation.
            //This method can be invoked from doInBackground(Params...) to publish updates on the UI thread while the background computation is still running.
            for(int i=0; i<100; i++){
                publishProgress(i);
                SystemClock.sleep(100);
                //thread will stop after given milli seconds
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            myProgressBar.setProgress(values[0]);
        }
    //Runs on the UI thread after publishProgress(Progress...) is invoked
    }

    Button buttonStart;
    ProgressBar progressBar1, progressBar2, progressBar3,progressBar4;
    MyAsyncTask asyncTask1, asyncTask2, asyncTask3,asyncTask4;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    //Indicates that Lint should treat this type as targeting a given API level, no matter what the project target is.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Variables, methods, and constructors, which are declared protected in a superclass can be accessed only by the subclasses
        // in other package or any class within the package of the protected members class.
        //void is a Java keyword.  Used at method declaration and definition to specify that the method does not return any type,
        // the method returns void.
        //onCreate Called when the activity is first created. This is where you should do all of your normal static set up: create views,
        // bind data to lists, etc. This method also provides you with a Bundle containing the activity's previously frozen state,
        // if there was one.Always followed by onStart().
        //Bundle is most often used for passing data through various Activities.
// This callback is called only when there is a saved instance previously saved using onSaveInstanceState().
// We restore some state in onCreate() while we can optionally restore other state here, possibly usable after onStart() has
// completed.The savedInstanceState Bundle is same as the one used in onCreate().

        super.onCreate(savedInstanceState);
// call the super class onCreate to complete the creation of activity like the view hierarchy
        setContentView(R.layout.activity_main);
        //R means Resource
        //layout means design
        //  main is the xml you have created under res->layout->main.xml
        //  Whenever you want to change your current Look of an Activity or when you move from one Activity to another .
        // The other Activity must have a design to show . So we call this method in onCreate and this is the second statement to set
        // the design
        ///findViewById:A user interface element that displays text to the user.
        progressBar1 = (ProgressBar)findViewById(R.id.progressbar1);
        progressBar2 = (ProgressBar)findViewById(R.id.progressbar2);
        progressBar3 = (ProgressBar)findViewById(R.id.progressbar3);
        progressBar4 = (ProgressBar)findViewById(R.id.progressbar4);

        buttonStart = (Button)findViewById(R.id.start);
        buttonStart.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final boolean API_LEVEL_11 = android.os.Build.VERSION.SDK_INT > 11;
//we will give the limit of android version. we will give progress for each bar
                progressBar1.setProgress(0);
                progressBar2.setProgress(0);
                progressBar3.setProgress(0);
                progressBar4.setProgress(0);
//creating the object for async task
                asyncTask1 = new MyAsyncTask(progressBar1);
                asyncTask2 = new MyAsyncTask(progressBar2);
                asyncTask3 = new MyAsyncTask(progressBar3);
                asyncTask4 = new MyAsyncTask(progressBar4);
//if pool is api level then the execute
                if(API_LEVEL_11)
                {
                    asyncTask1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    //An Executor that can be used to execute tasks in parallel.
                    asyncTask2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    asyncTask3.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    asyncTask4.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }

            }});

    }

    @SuppressLint("NewApi")
    private void StartAsyncTaskInParallel(MyAsyncTask task) {

        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//this method is two run the task parallely
    }

}