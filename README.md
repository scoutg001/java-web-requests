# Weather API Fetcher (Educational Project)

This Java project is a **learning exercise** focused on implementing a modular system for fetching weather data from the OpenWeatherMap API. It demonstrates key programming concepts such as the Decorator Pattern, API interaction, caching, logging, and rate limiting.

> ⚠️ **Note**: This project is for educational purposes only and may not be production-ready.

## ✨ Features

- **Modular Design** using the Decorator Pattern
- **In-Memory Caching** to reduce redundant API calls
- **Call Logging** for basic usage tracking
- **Rate Limiting** support (via `RateLimitedAPIFetcher`)
- **Flexible Parsing** using `OpenWeatherMapAPIParser`
- **Extensible Architecture** (swap out `APIFetcher` implementations easily)

## 🚀 Getting Started

### Prerequisites

- Java 17+
- An OpenWeatherMap API key

Set your API key as an environment variable:

```bash
export OPENWEATHER_API_KEY=your_api_key_here
```

### Building & Running

1. Clone the repository or copy the source files into your Java project.
2. Compile and run the `Main` class.

```bash
javac Main.java
java Main
```

## 🧠 Code Overview

### Interfaces

- `APIFetcher`: Defines the base interface for all fetchers.

### Core Components

- `SimpleAPIFetcher`: Basic HTTP fetcher.
- `CachedAPIFetcher`: Adds in-memory caching of API responses.
- `LoggedAPIFetcher`: Tracks and logs how many times each URL is requested.
- `RateLimitedAPIFetcher`: Enforces a limit on API calls.
- `FetcherResponse`: Encapsulates response data and status codes.
- `CacheObject<T>`: Generic cache wrapper with timestamp-based invalidation.
- `OpenWeatherMapAPIParser`: Parses temperature and condition info from JSON.

### Example Weather Query

The app fetches weather data for Erie, PA using OpenWeatherMap:

```java
String lat = "42.1255";
String lon = "-80.0843";
String url = String.format(WEATHER_API_URL, lat, lon, openWeatherAPIKey);

FetcherResponse response = fetcher.getURL(url);
```

## 📄 License

MIT License
