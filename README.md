# mars-rover
Java-based CLI application that downloads and stores images from the Mars Rover Curiosity API by reading in a text file of newline-separated dates. This application uses an image cache to prevent downloading images unnecessarily. Once all images have been downloaded, the application generates an HTML file and then opens it using the system's default browser.
<br />

Application takes two arguments to run:
1. argument 1 - absolute path to text file containing dates
2. argument 2 - absolute path to image cache. (note: the application checks the image cache by file name to see if a given image file exists. if it does, the application skips the download for that image)
<br />

Current acceptable date formats are:
- January 1, 2001 <br />
- January 01, 2001 <br />
- 01/01/01 (application assumes 21st century if only 2-digit year is provided) <br />
- 01/01/2001 <br />
- Jan-1-2001 <br />
- Jan-01-2001 <br />

If an unnacceptable date / date format is provided, the application will just log it and still attempt to fetch images for any remaining dates. If no acceptable dates are provided, i.e. no images are found, then the application exits and does not generate an HTML file.
<br />

## Prerequisites
[Java v14.0.1](https://www.oracle.com/java/technologies/javase-downloads.html) <br />
[Gradle](https://gradle.org/install/) <br />
<br />

## Run
`gradle wrapper` (if running for the first time) <br />
`./gradlew run --args="/path/to/dates.txt /path/to/image/cache"` <br />
<br />

## Test
`./gradlew test` <br />
<br />

## Static Analysis (currently only running `checkstyle`)
`./gradlew check`
