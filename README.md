

# Spring Tech Showcase

Spring Tech Showcase for LEAN Tech

## Installation

1. Clone the repository:
    
    `git clone https://github.com/bfinleyui/spring-tech-showcase.git`
    
2. Navigate to the project directory:
    
    `cd spring-tech-showcase`    

3. Build the project using Gradle:
    
    `./gradlew build`
    

## Usage

1. Run the application:
    
    `./gradlew bootRun`
    
2. Open your web browser and navigate to `http://localhost:8080`.

## Running Tests

To run the tests, use the following command:

`./gradlew test`


## Technologies Used

- Java 21
- Spring Boot 3.4.2
- Thymeleaf
- Bootstrap
- Gradle
- JUnit 5
- Mockito
- JaCoCo

## Further Considerations

Given unlimited time and resources, I would consider the following:

- Nicer front-end, using flexbox to make the cascading gallery look a little nicer, particularly with different aspect 
    ratios
- If using a different technology, would use Ionic and angular/react to build a PWA that can then compile down into 
native IOS and Android apps.  Angular and its data binding would make a pretty fun project for this, being able to 
 maintain a single instance of the data, and just filter what's displayed in whatever carousel/grid/gallery/etc is 
chosen.  Would be trivial, code-wise.  Most of the time would be spent setting up infrastructure.
- Photos themselves are fairly large.  Leaning on the browser for resizing, if there's a lot more photos, might want to 
  use a server-side resizer to present a smaller version for faster loading.
- Considered smaller thumbnails and a modal, that's personal preference.
- More test coverage, depending on other complexities of the app.  
- Look into caching some of the options for the JSON, since it's not entirely dynamic.  Could be a performance increase.