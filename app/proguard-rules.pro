# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# https://rezaramesh.medium.com/unlocking-the-power-of-proguard-in-android-a-comprehensive-guide-78dffddc119e
# Improving App Startup Time
-optimizations !method/inlining/

# Enhancing App Security
-keep class com.example.widget_compose.** {
    *;
}

# ProGuard is a tool that helps optimize and obfuscate your code in Android applications, making it smaller and more efficient. While ProGuard can improve the functionality of your APK in terms of performance and security, it's essential to use it carefully. Here are some tips for improving APK functionalities through the proguard-rules.pro file:
# Keep Critical Classes and Methods: Identify critical classes, methods, or resources that should not be obfuscated or stripped by ProGuard. Use the -keep option to specify rules for preserving these components. For example:

#-keep class com.example.MyClass {
#    public <methods>;
#}

#Remove Unused Code: ProGuard can remove unused code, which reduces the APK size and improves runtime performance. Enable code shrinking using -dontshrink and -dontoptimize flags:
-dontshrink
-dontoptimize

#Obfuscate Code: Obfuscation renames classes, methods, and fields to make reverse engineering more challenging. This is enabled by default, but you can enhance it further using
-obfuscationdictionary # and
-classobfuscationdictionary # options.

#Keep Android Components: Ensure that Android components (activities, services, receivers, etc.) are not removed. Use rules like:
#-keep class com.example.activities.** { *; }
#-keep class com.example.services.** { *; }

#Keep Enumerations: If you have enumerations, ensure they are kept intact:
#-keepclassmembers enum * {
#    public static **[] values();
#    public static ** valueOf(java.lang.String);
#}

# Handle Third-Party Libraries: If you're using third-party libraries, ensure that ProGuard doesn't strip or obfuscate them. Many libraries provide ProGuard rules that you can include in your proguard-rules.pro.

# Optimize Reflection: If your app uses reflection, add rules to keep the necessary classes, methods, or fields:
#-keepclassmembers class com.example.MyClass {
#    <fields>;
#    <methods>;
#}

#Handle Parcelable and Serializable: If you're using Parcelable or Serializable objects, ensure they are preserved:
#-keep class * implements android.os.Parcelable {
#    public static final ** CREATOR;
#}

#-keepnames class * implements java.io.Serializable

#Keep Native Code: If your app includes native code (C/C++), make sure it's not removed:
#-keepclasseswithmembernames class * {
#    native <methods>;
#}

#Keep Required Resources: If your app uses specific resources like layouts, drawables, or assets, use -keep rules to retain them:
#-keepclassmembers class **.R$* {
#    public static <fields>;
#}
#-keepclassmembers class **.R {
#    public static <fields>;
#}

#Testing and Debugging: Test your app thoroughly after applying ProGuard rules to ensure that functionality is not broken. Use debugging options if needed, like -dontobfuscate.

#Keep Special Libraries: Some libraries may require specific ProGuard rules. Refer to their documentation for guidance.

#Keep Kotlin-Related Code: If your app uses Kotlin, consider adding rules to preserve Kotlin-related code:

# -keep class kotlin.** { *; }
# -keep class kotlinx.** { *; }
# Regularly Update ProGuard: Keep ProGuard up to date with the latest version to benefit from improvements and bug fixes.
# Monitor and Optimize: Continuously monitor your app's behavior and performance after applying ProGuard. Make adjustments as needed.
# Remember that ProGuard can significantly impact your app's behavior, so it's crucial to thoroughly test your app after applying ProGuard rules to ensure that functionality is not compromised.
