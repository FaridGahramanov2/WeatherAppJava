# Weather Forecast Application

## Overview
The **Weather Forecast Application** is a Java-based weather forecasting tool that utilizes the OpenWeatherMap API to fetch and display real-time weather data. Built with Java Swing for the GUI and following the MVC architectural pattern, this application demonstrates the effective use of various design patterns to create a maintainable and scalable codebase.

---

## Project Structure
The project adheres to the **Model-View-Controller (MVC)** architectural pattern:
- **Model**: Manages application data and business logic.
- **View**: Handles the graphical user interface (GUI).
- **Controller**: Bridges the model and view by processing user inputs and updating the view accordingly.

---


## Features
- Real-time weather data fetching
- Temperature unit conversion (Metric/Imperial)
- Dynamic background colors based on weather conditions
- Responsive UI with data caching
- Country flags and weather icons
- Error handling and user feedback


----

### Model
- `WeatherData`: Core data model for weather information
- `WeatherConditions`: Weather state definitions
- Data handling and business logic

### View
- `MainFrame`: Main application window
- `WeatherPanel`: Weather information display
- Components:
- `GradientPanel`: Dynamic background
- `ImageLabelGenerator`: Weather icon handling
- Utility:
- `ColorClimate`: Background color management
- `CountryImageResolver`: Country flag handling
- `WeatherStateResolver`: Weather state management

### Controller
- `WeatherController`: Main application controller
- Command implementations for user actions
- Strategy implementations for temperature processing

## Design Patterns Implemented

### 1. Creational Patterns
#### Singleton
- `ApiClient`: Manages API requests


#### Builder
- `RequestBuilder`: Constructs API request URLs

### 2. Behavioral Patterns
#### Observer
- `WeatherObserver`: Interface for weather data updates
- `WeatherSubject`: Weather data notification system

#### Strategy
- `WeatherProcessingStrategy`: Temperature unit conversion
- Implementations:
- `MetricProcessor`: Celsius conversion
- `ImperialProcessor`: Fahrenheit conversion

#### Command
- `Command`: Interface for user actions
- Implementations:
- `RefreshWeatherCommand`: Updates weather data
- `SaveSettingsCommand`: Saves user preferences

### 3. Structural Patterns
#### Adapter
- `ApiResponseAdapter`: Converts API JSON to WeatherData

#### Facade
- `WeatherServiceFacade`: Simplifies weather service operations

#### Proxy
- `WeatherApiProxy`: Handles API caching and requests

## Technical Details
- Language: Java
- GUI Framework: Java Swing
- API: OpenWeatherMap
- External Libraries: JSON for API parsing

## Key Features
- Dynamic Unit Conversion: Switch between Celsius and Fahrenheit
- Smart Caching: Reduces API calls
- Responsive Design: Adapts to window resizing
- Error Handling: User-friendly error messages
- Visual Feedback: Loading cursors and status messages

## How It Works
1. User enters a city name
2. Application fetches data from OpenWeatherMap API
3. Data is processed and cached
4. UI updates with:
- Temperature
- Humidity
- Pressure
- Weather description
- Country flag
- Weather icon
5. Background changes based on weather conditions