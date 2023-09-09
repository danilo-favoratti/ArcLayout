# ArcLayout

[![Release](https://jitpack.io/v/danilo-favoratti/ArcLayout.svg)](https://jitpack.io/#danilo-favoratti/ArcLayout)

Composable ArcLayout library for Android.

## Demo Application 

[![Demo App][arclayout-demo-app_png]](https://play.google.com/store/apps/details?id=com.favoratti.arclayout)
[https://play.google.com/store/apps/details?id=com.favoratti.arclayout](https://play.google.com/store/apps/details?id=com.favoratti.arclayout)

![ArcLayout Demo][arclayout_png]

## API Level

API Level 28+ compiled with SDK 34. 

**Please, use compileSdk = 34 or higher.**

# Usage
Add the repository and dependency to the gradle configuration.

## Gradle Config (Groovy)

### Adding the repository to build.gradle (root)
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

### Adding the dependency to build.gradle (app module)

```
dependencies {
    ...
    implementation 'com.github.danilo-favoratti:ArcLayout:0.1.0'
}
```

## Gradle Config (Kotlin/KTS)

### Adding the repository to settings.gradle.kts (root)

```
dependencyResolutionManagement {
     repositories {
        ...
        maven { url = URI("https://jitpack.io") }
    }
}
```

### Adding the dependency build.gradle.kts (app module)

```
dependencies {
    implementation("com.github.danilo-favoratti:ArcLayout:0.1.0")
}
```

## ArcLayout

Include the ArcLayout widget in your compose.

```
ArcLayout(
    modifier = Modifier.background(color = Color.Black),
    alignment = Alignment.TOP,
    radius = 100.dp
) {
    // ... your items, example:
    Text(text = "A")
    Text(text = "B")
    Text(text = "C")
    Text(text = "D")
}
```

| attr             | description                                                                                               |
|:-----------------|:----------------------------------------------------------------------------------------------------------|
| modifier         | A modifier to be applied to the Layout.                                                                   |
| alignment        | Predefined alignment (LEFT, RIGHT, TOP, BOTTOM, CENTER, TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT or BOTTOM_RIGHT) |
| radius           | Radius of the layout, from the center os the Arc to the middle of your items.                             |

## CustomArcLayout

You can create it with custom parameters.

```
 CustomArcLayout(
    modifier = Modifier.background(color = Color.Black),
    parameters = 
        Parameters.builder(radius = 100.dp)
            .anchorAngle(235f)
            .sweepAngle(130f)
            .reverse(false)
            .build()
) {
    // ... your items, example:
    Text(text = "A")
    Text(text = "B")
    Text(text = "C")
    Text(text = "D")
}
```

| attr        | description                                                                             |
|:------------|:----------------------------------------------------------------------------------------|
| modifier    | A modifier to be applied to the Layout.                                                 |
| anchorAngle | Which angle your items will start to be drawn. 270 means clock noon. 0 means 3PM clock. |
| sweepAngle  | Total angle of your Arc. 180 means half Arc. 360, full arc.                             |
| radius      | Size radius of the layout, from the center of the Arc to the middle of your items.      |
| reverse     | A boolean determining the direction which your items will be displayed.                 |

# Inspired By
https://github.com/ogaclejapan/ArcLayout

# LICENSE

```
Copyright (C) 2023 favoratti

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
[arclayout_png]: https://raw.githubusercontent.com/danilo-favoratti/ArcLayout/main/art/arclayout.png
[arclayout-demo-app_png]: https://raw.githubusercontent.com/danilo-favoratti/ArcLayout/main/art/arclayout-demo-app.png