package springboot.rest.greeting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

    @Test
    void home() {
        // Arrange
        HomeController homeController = new HomeController();
        // Act
        String homeResponse = homeController.home();
        // Assert
        assertEquals("Springboot Home", homeResponse);
    }
}