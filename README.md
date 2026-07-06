# BugScreen — Android integration example

A reference for wiring the [BugScreen](https://bugscreen.app) bug-reporting SDK into an Android app.
If you're evaluating BugScreen or setting it up for the first time, this repo shows every integration
point in a working app. Copy the five spots below and you have a full integration.

A tester takes a screenshot anywhere in the app and the report screen opens. Reports include device
info, your custom data, and a breadcrumb log. Testers never leave the app.

The SDK is wired into a small but complete travel app (Kotlin, Jetpack Compose / Material 3), so you
can see it working in a real navigation stack. [More on the demo app below.](#the-demo-app)

## The integration

Everything BugScreen-related lives in a handful of clearly-commented spots:

| # | What | Where |
|---|------|-------|
| 1 | Initialize once at startup | [`TravelApp.kt`](app/src/main/java/app/bugscreen/example/travel/TravelApp.kt) → `BugScreenSDK.start(...)` |
| 2 | Request screenshot permission (Android 13+) | [`MainActivity.kt`](app/src/main/java/app/bugscreen/example/travel/MainActivity.kt) → `requestScreenshotPermission(...)` |
| 3 | Attach user / session context | [`TravelApp.kt`](app/src/main/java/app/bugscreen/example/travel/TravelApp.kt) → `registerSignedInUser()` (`setCustomData`) |
| 4 | Log breadcrumbs at meaningful events | [`BugScreenBreadcrumbs.kt`](app/src/main/java/app/bugscreen/example/travel/bugscreen/BugScreenBreadcrumbs.kt) (`BugScreenSDK.log`) |
| 5 | Manual "Report a problem" entry point | [`ProfileScreen.kt`](app/src/main/java/app/bugscreen/example/travel/ui/profile/ProfileScreen.kt) → `openBugReportScreen(context)` |

Automatic screenshot detection needs no wiring beyond step 1; it's on by default.

### Dependency setup (debug vs. release)

```kotlin
// app/build.gradle.kts
dependencies {
    debugImplementation("app.bugscreen:android:1.3.0")        // full SDK in debug builds
    releaseImplementation("app.bugscreen:android-no-op:1.3.0") // no-op stub in release builds
}
```

BugScreen is a dev/QA tool. The `no-op` artifact exposes the same API and does nothing, so release
builds ship no bug-reporting code to production users. Because the API is identical, your app code
never branches on build type. The API key is read from `local.properties` and exposed via a
`BUGSCREEN_API_KEY` `buildConfigField`, so no key is committed to source control.

## Run it

1. Clone this repo and open it in Android Studio (latest stable), or build from the CLI.
2. Add your API key. Copy the sample properties file and paste your key from the
   [BugScreen console](https://bugscreen.app):
   ```bash
   cp local.properties.sample local.properties
   # then edit local.properties:
   #   bugscreen.apiKey=fb_your_api_key_here
   ```
   You can leave the key blank. The app still builds and runs; you just can't upload reports.
3. Run the `app` configuration on a device or emulator (Android 7.0 / API 24 or newer).
4. Try the headline flow: open the app and take a screenshot (Power + Volume-Down, or
   `adb shell input keyevent 120`). The report screen opens by itself.

> **JDK note:** this project targets JDK 17 (AGP 8.11). Android Studio's bundled JDK works out of
> the box. From the command line, run Gradle with a JDK 17–21 (e.g. `JAVA_HOME=<jdk17>`). The
> compile toolchain is pinned to 17 and auto-provisioned if you don't have it installed.

## The demo app

**BugScreen Travel** is a small travel-discovery app that gives the SDK a place to live. Browse
destinations, save favourites, and open a trip itinerary, plus a manual "Report a problem" entry
point in the profile. Built with Kotlin, Jetpack Compose (Material 3), and Navigation-Compose. All
content is bundled and static, so no backend, accounts, or network are needed to run it.

| Explore | Destination | Report (automatic) | Profile |
|---|---|---|---|
| Feed of destinations | Photo, highlights, save | Opens on screenshot | Manual "Report a problem" |

### Project layout

```
app/src/main/java/app/bugscreen/example/travel/
  TravelApp.kt            # Application — SDK init + custom data  (integration #1, #3)
  MainActivity.kt         # Single Activity host — permission     (integration #2)
  TravelViewModel.kt      # App-scoped state over the repository
  bugscreen/              # BugScreenBreadcrumbs — logging wrapper (integration #4)
  navigation/             # Routes + bottom-nav NavHost
  data/                   # Static sample data + in-memory repository
  ui/
    explore/ detail/ saved/ trips/ profile/   # screens (#5 lives in profile/)
    components/           # cards, chips, procedural HeroImage
    theme/                # Material 3 colours, type, light/dark theme
```

### Notes & limitations

- **Static data.** Destinations, trips, and the user are hard-coded in
  [`SampleData.kt`](app/src/main/java/app/bugscreen/example/travel/data/SampleData.kt).
- **No persistence.** Saved destinations live in memory and reset when the process is killed.
- **No bundled photos.** Destination "images" are gradients generated from each place's category
  (see `ui/components/HeroImage.kt`), which keeps the repo small and free of image licensing.
- **Uploads need a real key.** With a blank `bugscreen.apiKey` the report screen still opens; it
  just can't submit. Add your `fb_...` key to see reports land in your BugScreen console.
