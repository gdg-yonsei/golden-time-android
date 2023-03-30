<h1 align="center">Golden Time 📱</h1>
<h3 align="center">Share a manual, share a life</h3>
<p align="center">
  <img src="https://user-images.githubusercontent.com/11978494/228843932-c59e03fb-d4e7-458d-a548-58e80583a7ea.png" alt="icon" width="250" height="250">
</p>

## Overview

*Golden Time* is a service helping rescuers to save the patient with the proper manual. By sharing the patient's manual to nearby people when an emergency occurs, it shortens rescuer's hesitating time and prevents some critical dangers such as a WRONG ACTION. And this is the main application of Golden Time running in Android. 

The overall process is as follows.
1. Register a disease with related manuals.
2. If SOS triggered (by manually, irregular heart rate detection, or fall detection), send the patient's medical ID and manauls to nearby people.
3. People around the patient receive a message containing the patient's medical ID and a manual registered by the patient.
4. Automatically calling to 911, the rescuers help the patient to be saved with the proper manual.

In addition to the main flow, users can read and study various diseases with an article tab.
With our wearable application [WearOS for Golden Time](https://github.com/gdsc-ys/golden-time-wearos), the patient can monitor his or her heart rates and trigger an emergency SOS automatically if it detects an irregular heart rate or a falling activity.

## Architecture

It follows a MVVM pattern and a layered architecture.
First, it consists of 5 repositories (Data layer) and 5 usecases (Domain layer).

Each repository has a role as follows.
- Case repository (API) : Get emergency cases and related manuals.
- Disease repository (API) : Get diseases containing related emergency cases.
- Location repository (Fused) : Get current location data.
- Profile repository (Local store) : Get an user's profile including medical IDs and diseases.
- SOS repository (API) : Get ongoing SOS cases nearby.

Each usecase has a role as follows using the above repositories.
- Article usecase (Case repository, Diseaes repository) : Read all about emergency cases, diseases, and related manuals.
- Message usecase (FCM) : Subscribe nearby SOS notifications.
- Patient usecase (SOS repository, Profile repository, Location repository) : As a patient, request an emergency SOS and watch the current states of rescue.
- Rescue usecase (SOS repository, Disease repository, Case repository, Location repository) : As a rescuer, get the patient's location and manual, and manage the SOS states.
- Profile usecase (Profile repository, Disease repository) : Set user's medical ID and register diseases they have.

With the above business logics, we have 5 big screens (UI layer).
- Home : Trigger SOS by manually.
- Profile : Read and edit my medical ID.
- Article : Read the overview of diseases with symptoms, causes, and manuals of its cases. Then bookmark some diseases as my diseases.
- SOS : After triggering SOS, watch the SOS state.
- Rescue : Find the patient using a map and follow the manual for him or her.

## Techniques

- Android Compose
- DataStore
- Material Design 3
- Retrofit2
- Firebase (Messaging service)
- Google maps
- Youtube iframe API
- with [WearOS](https://github.com/gdsc-ys/golden-time-wearos)...
- with [Server](https://github.com/gdsc-ys/golden-time-backend)...

## Screenshots

### Main Page

| | |
|:-:|:-:|
| | |

## Getting started

1. Clone the project.
  ```
  
  ```

2. l

## Permission

- Internet
- Location
- Notification
