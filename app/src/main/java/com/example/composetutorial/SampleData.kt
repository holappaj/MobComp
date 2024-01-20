import com.example.composetutorial.MainActivity
import com.example.composetutorial.UserMessage

/**
 * SampleData for Jetpack Compose Tutorial 
 */
object SampleData {
    // Sample conversation data
    val conversationSample = listOf(
        UserMessage(
            "holappaj",
            "Find me on GitHub with the same user account name"
        ),
        UserMessage(
            "Lexi",
            "Test...Test...Test..."
        ),
        UserMessage(
            "Lexi",
            """List of Android versions:
            |Android KitKat (API 19)
            |Android Lollipop (API 21)
            |Android Marshmallow (API 23)
            |Android Nougat (API 24)
            |Android Oreo (API 26)
            |Android Pie (API 28)
            |Android 10 (API 29)
            |Android 11 (API 30)
            |Android 12 (API 31)""".trim()
        ),
        UserMessage(
            "Lexi",
            """I think Kotlin is my favorite programming language.
            |It's so much fun!""".trim()
        ),
        UserMessage(
            "Lexi",
            "Searching for alternatives to XML layouts..."
        ),
        UserMessage(
            "Lexi",
            """Hey, take a look at Jetpack Compose, it's great!
            |It's the Android's modern toolkit for building native UI.
            |It simplifies and accelerates UI development on Android.
            |Less code, powerful tools, and intuitive Kotlin APIs :)""".trim()
        ),
        UserMessage(
            "Lexi",
            "It's available from API 21+ :)"
        ),
        UserMessage(
            "Lexi",
            "Writing Kotlin for UI seems so natural, Compose where have you been all my life?"
        ),
        UserMessage(
            "Lexi",
            "Android Studio next version's name is Arctic Fox"
        ),
        UserMessage(
            "Lexi",
            "Android Studio Arctic Fox tooling for Compose is top notch ^_^"
        ),
        UserMessage(
            "Lexi",
            "I didn't know you can now run the emulator directly from Android Studio"
        ),
        UserMessage(
            "Lexi",
            "Compose Previews are great to check quickly how a composable layout looks like"
        ),
        UserMessage(
            "Lexi",
            "Previews are also interactive after enabling the experimental setting"
        ),
        UserMessage(
            "Lexi",
            "Have you tried writing build.gradle with KTS?"
        ),
    )
}
