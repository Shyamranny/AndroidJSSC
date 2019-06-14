# Android JSSC
This is a wrapper projcet of java simple serial connector - [JSSC](https://code.google.com/archive/p/java-simple-serial-connector/) to work in android platform. You may need [NDK](https://developer.android.com/ndk/guides) for building this project.

If you want to build the jssc package in your environment, please modify app/build.gradle file, add required filters under

```
ndk {
	abiFilters "armeabi-v7a", "x86"
}
```

While building, the cpp files will be compiled to .so files (for each platform) and will be added to the generated apk. Please note that I have not added the searil port read/write functionality. 

