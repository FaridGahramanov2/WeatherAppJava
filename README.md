# Weather Forecast Application

## Overview
The **Weather Forecast Application** is a Java-based project designed to fetch, process, and display weather data from the OpenWeatherMap API. The application will use the **MVC architectural pattern** and various **design patterns** to ensure a scalable, maintainable, and efficient codebase.

---

## Project Structure
The project adheres to the **Model-View-Controller (MVC)** architectural pattern:
- **Model**: Manages application data and business logic.
- **View**: Handles the graphical user interface (GUI).
- **Controller**: Bridges the model and view by processing user inputs and updating the view accordingly.

---

## Design Patterns Used

### 1. Creational Patterns
#### Singleton
- **Purpose**: Ensure a single instance of critical components like the API client or caching manager.
- **Usage**:
  - A `WeatherDataCache` to store weather data and prevent redundant API calls.
  - An `ApiClient` singleton for managing API requests.
- **Why**: Prevents multiple instances of key resources, ensuring consistent data and efficient resource usage.

#### Builder
- **Purpose**: Simplify the creation of complex objects such as API requests.
- **Usage**:
  - A `RequestBuilder` class to construct API requests with varying parameters (e.g., city name, units, language).
- **Why**: Provides a clean way to build objects step-by-step without overwhelming constructors.

---

### 2. Behavioral Patterns
#### Observer
- **Purpose**: Automatically update the view when the model (weather data) changes.
- **Usage**:
  - The `WeatherData` class acts as the subject.
  - GUI components like `WeatherPanel` subscribe as observers to reflect data updates in real time.
- **Why**: Decouples the model from the view, improving flexibility and maintainability.

#### Strategy
- **Purpose**: Allow interchangeable algorithms for processing weather data, such as unit conversions (metric to imperial).
- **Usage**:
  - A `WeatherProcessingStrategy` interface with implementations like `MetricProcessor` and `ImperialProcessor`.
- **Why**: Enables dynamic switching of data-processing methods without altering the core logic.

#### Command
- **Purpose**: Encapsulate user actions like refreshing data or changing settings as independent commands.
- **Usage**:
  - Commands like `RefreshWeatherCommand` and `SaveSettingsCommand` will manage user-triggered actions.
- **Why**: Supports undo functionality and simplifies user interaction handling.

---

### 3. Structural Patterns
#### Adapter
- **Purpose**: Bridge the gap between the API's JSON responses and the application's data model.
- **Usage**:
  - An `ApiResponseAdapter` to convert raw JSON responses into `WeatherData` objects.
- **Why**: Ensures compatibility between the API and the application's internal structure.

#### Facade
- **Purpose**: Provide a unified interface to simplify backend operations like fetching weather data.
- **Usage**:
  - A `WeatherServiceFacade` to encapsulate API calls, error handling, and caching.
- **Why**: Simplifies the interface for accessing complex subsystems.

#### Proxy
- **Purpose**: Manage API calls and implement caching to minimize redundant requests.
- **Usage**:
  - A `WeatherApiProxy` to cache responses and rate-limit API calls.
- **Why**: Enhances performance by reducing network overhead.

#### Composite
- **Purpose**: Represent hierarchical weather data such as current weather, hourly forecasts, and daily forecasts.
- **Usage**:
  - A `WeatherComponent` interface with implementations like `CurrentWeather`, `HourlyForecast`, and `DailyForecast`.
- **Why**: Treats individual and grouped weather data uniformly, simplifying the UI rendering logic.

---

### Architectural Pattern
#### Model-View-Controller (MVC)
- **Purpose**: Separate concerns to ensure maintainability and scalability.
- **Usage**:
  - `Model`: Contains `WeatherData`, `WeatherService`, and caching logic.
  - `View`: Implements GUI components like `WeatherPanel`.
  - `Controller`: Manages interactions between the model and view.
- **Why**: Provides a clean separation of concerns, enabling independent development and testing of components.

---

## Why These Patterns?
These patterns were chosen to address specific challenges in developing a weather application:
- **Singleton** ensures efficient use of resources.
- **Builder** simplifies object creation for dynamic API requests.
- **Observer** automates data synchronization between the model and view.
- **Strategy** allows flexibility in data processing.
- **Command** encapsulates user actions for better control and undo functionality.
- **Adapter**, **Facade**, and **Proxy** streamline API interactions and enhance performance.
- **Composite** simplifies handling of hierarchical weather data.

---

## Next Steps
1. **Create the package structure** to implement the MVC architecture and patterns.
2. Begin implementing foundational classes like `WeatherData`, `WeatherController`, and `WeatherServiceFacade`.
3. Set up API integration using `ApiClient` and `ApiResponseAdapter`.