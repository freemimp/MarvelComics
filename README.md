# Instructions

### 1. Setup

In order to run this app you need to add to project root file `secure.properties` that have content below

`publicApiKey="yourpublicapikey"`
`privateApiKey="yourprivateapikey"`
`baseUrl="http://gateway.marvel.com/v1/public/"`

You can get these keys in here https://developer.marvel.com/, you may need to register.

### 1. App description

- This app is build using Marvel api to load list of 20(default value) Comics.
When Clicking on item you are taken to details screen, that for 
simplicity shows you the image or thumbnail if image url is absent

- I used jetpack compose, hilt, jetpack compose navigation. Junit5, Mockk and Kotlin coroutines and flow
- App is split by the feature,
- A separate module created for snapshots, as it does have conflicts with some dependencies like Roboelectric.
- This code is WIP