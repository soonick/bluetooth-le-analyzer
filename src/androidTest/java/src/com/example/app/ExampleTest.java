package com.example.app;

import android.test.ActivityInstrumentationTestCase2;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class ExampleTest
extends ActivityInstrumentationTestCase2<Example> {
    public ExampleTest() {
        super("com.example.app", Example.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testExample() {
      onView(withText("Hello"));
    }
}
