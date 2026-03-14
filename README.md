# Almosafer Automation Testing Project

## Overview

This project contains automated test scenarios for the Almosafer travel website using Selenium WebDriver with Java and TestNG.

The goal of this project is to practice web automation testing by simulating real user actions such as searching for flights and hotels.

## Website

https://sa.almosafer.com/

## Tools & Technologies

* Java
* Selenium WebDriver
* TestNG
* Maven (optional if you used it)
* Edge / Chrome WebDriver

## Test Scenarios

### 1. Close Popup

* Open the website
* Detect and close the initial popup if it appears

### 2. Change Language

* Open the menu
* Change the website language between Arabic and English
* Verify the language change using assertions

### 3. Flight Search

* Enter random origin and destination
* Select a location from autocomplete
* Prepare the search inputs

### 4. Date Selection

* Open the date picker
* Navigate between months
* Select specific departure and return dates

### 5. Hotel Search

* Navigate to the Hotels tab
* Enter a random city:

  * Amman
  * Dubai
  * Jeddah
  * Muscat
* Select check-in and check-out dates

### 6. Guests and Rooms Selection

* Open the rooms selector
* Choose number of adults and children
* Select children ages

### 7. Open Hotel Result

* Perform hotel search
* Open the first hotel result from the list

## Features Implemented

* Random test data generation
* Handling dynamic elements
* Working with autocomplete fields
* Handling date picker
* Handling dropdown selections
* Using assertions to validate actions

## How to Run the Project

1. Clone the repository
2. Open the project in Eclipse or IntelliJ
3. Run the TestNG test class

## Author

Mohammad Ahmad Taha
