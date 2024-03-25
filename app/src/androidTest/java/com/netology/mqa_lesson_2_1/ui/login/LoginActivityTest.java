package com.netology.mqa_lesson_2_1.ui.login;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.netology.mqa_lesson_2_1.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(LoginActivity.class);
    //Запускается приложение и закрывается приложени

    //Инициализирует за четыре шага - выделяется подкласс - всеми нулями
    //Запускается инициализация переменных
    //Запускается

    @Test
    public void loginActivityTest() {
        String USER_NAME = "test@mail.ru";
        String USER_PASSWORD = "password";

        //Тест может упасть если объект невидимый
        //  ViewInteraction appCompatEditText = onView(
        //       allOf(withId(R.id.username),
        ///              childAtPosition(
        //                     allOf(withId(R.id.container),
        //                             childAtPosition(
        //                                    withId(android.R.id.content),
        //                                    0)),
        //                     0),
        //             isDisplayed()));
        //Все что нужно:
        ViewInteraction appCompatEditText = onView(withId(R.id.username));
        appCompatEditText.check(matches(isDisplayed())); //делать всегда
        appCompatEditText.perform(replaceText(USER_NAME), closeSoftKeyboard());

        //  ViewInteraction appCompatEditText2 = onView(
        //          allOf(withId(R.id.password),
        //                  childAtPosition(
        //                         allOf(withId(R.id.container),
        //                                childAtPosition(
        //                                         withId(android.R.id.content),
        //                                          0)),
        //                          1),
        //                   isDisplayed()));
        //Все что нужно:
        ViewInteraction appCompatEditText2 = onView(withId(R.id.password));
        appCompatEditText.check(matches(isDisplayed())); //делать всегда
        appCompatEditText2.perform(replaceText(USER_PASSWORD), closeSoftKeyboard());

        //ViewInteraction materialButton = onView(
        //        allOf(withId(R.id.login), withText("Sign in or register"),
        ////                childAtPosition(
        //                        allOf(withId(R.id.container),
        //                                 childAtPosition(
        //                                       withId(android.R.id.content),
        //                                       0)),
        //                       2),
        //              isDisplayed()));
        //Все что нужно:
        ViewInteraction materialButton = onView(withId(R.id.login));
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(click());

        //ViewInteraction appCompatEditText3 = onView(
        //        allOf(withId(R.id.password), withText("password"),
        //                childAtPosition(
        //                        allOf(withId(R.id.container),
        //                                 childAtPosition(
        //                                         withId(android.R.id.content),
        //                                         0)),
        //                         1),
        //                isDisplayed()));
        // appCompatEditText3.perform(pressImeActionButton());

        ViewInteraction textView = onView(withId(R.id.result));
        textView.check(matches(isDisplayed()));
        textView.check(matches(withText("Welcome !" + USER_NAME)));


        //ViewInteraction textView = onView(
        //        allOf(withId(R.id.result), withText("Welcome !test@mail.ru"),
        //                withParent(allOf(withId(R.id.container),
        //                        withParent(withId(android.R.id.content)))),
        //                isDisplayed()));
        // textView.check(matches(withText("Welcome !test@mail.ru")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
