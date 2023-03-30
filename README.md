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

| Profile Page | Home Page | Article Page |
|:-:|:-:|:-:|
| ![Profile](https://user-images.githubusercontent.com/11978494/228888455-bc89111f-173e-4347-a2aa-ffc2ff8ef6fb.jpeg) | ![SOS Trigger](https://user-images.githubusercontent.com/11978494/228888669-29e38ed8-398d-47f2-a591-2cbdd6eea914.jpeg) | ![Article](https://user-images.githubusercontent.com/11978494/228888241-2e5561e1-59c4-4905-bc87-1d0fe0e270c8.jpeg) |

### SOS Detection

| Fall detection | Irregular heart rate detection | Waiting timer |
|:-:|:-:|:-:|
| ![Fall detected](https://user-images.githubusercontent.com/11978494/228889216-7bbe21a1-1a78-40c4-9f96-acb0e26773d7.jpeg) | ![Heart rate detected](https://user-images.githubusercontent.com/11978494/228889285-98e51cb4-b931-4708-8325-a8bf29568a09.jpeg) | ![Fall detected timer](https://user-images.githubusercontent.com/11978494/228889327-0d38eeba-66f6-46af-b56c-e280cd9aa754.jpeg) |

### SOS/Rescue

| SOS Triggered | SOS Waiting | Rescue |
|:-:|:-:|:-:|
| ![SOS](https://user-images.githubusercontent.com/11978494/228889564-c33748a7-f776-40fd-8b1d-70fdb60efa1f.jpeg) | ![SOS Watch](https://user-images.githubusercontent.com/11978494/228891912-ad201e9e-2216-412d-9f22-771d08f6be98.jpeg) | ![Rescue](https://user-images.githubusercontent.com/11978494/228890502-074d62c9-d040-4c29-9275-7ea569bba46d.jpeg) |

## Videos

### SOS Triggered by manually

https://user-images.githubusercontent.com/11978494/228889995-b29650d8-b2c7-49be-8b63-29ad9aa2b52e.mp4

### After accepting SOS

https://user-images.githubusercontent.com/11978494/228890652-6afb2edf-26c6-406a-9350-fba153e1b6a0.mp4

## Getting started

1. Clone the project.

    ```
    git clone https://github.com/gdsc-ys/golden-time-android.git
    ```

2. Open in Android Studio.

3. Inject private keys.

    - local.properties
    - google-services.json

4. Sync gradle dependencies.

5. Build and enjoy!

## Required

### Prerequisites
- Minimum SDK version : 24
- Target SDK version : 33

### Permission
- Internet
- Location
- Notification
