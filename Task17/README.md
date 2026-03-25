# Task 17 - Android scaffold (Kotlin)

Scaffold project for a long-term end-to-end app with:
- 3 fragments
- `BottomNavigationView` navigation
- app icon + Android Splash Screen
- enabled ViewBinding

## App idea

**Theme:** Rick and Morty character explorer

Planned 3 screens:
1. **Filter/Favorites** - search field and filters for character list (optionally favorites only).
2. **Characters List** - list with avatar image, name and status.
3. **Character Details** - full character profile for selected item.

## API

- API: [Rick and Morty API](https://rickandmortyapi.com/documentation)
- Base URL: `https://rickandmortyapi.com/api/`
- Main endpoint example: `GET /character`

## What is already implemented

- Single activity architecture with 3 fragments:
  - `SearchFragment`
  - `ListFragment`
  - `DetailFragment`
- Switching fragments via `BottomNavigationView`.
- ViewBinding enabled in `app/build.gradle.kts`.
- Splash configured through theme + `installSplashScreen()` in `MainActivity`.
- Launcher icon resources added (`mipmap-anydpi-v26` + vector drawables).

## Next steps

1. Add networking layer (`Retrofit` + `OkHttp` + models).
2. Replace placeholder fragment UI with real content.
3. Add RecyclerView adapters and click navigation to details.
4. Add state handling (`ViewModel` + `StateFlow`).
