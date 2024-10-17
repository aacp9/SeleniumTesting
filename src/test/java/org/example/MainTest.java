package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    //Crea el objeto del driver, se crea fuera del Before para que pueda se usada
    //por cualquier metodo
    static WebDriver driver; //se crea objeto

    @BeforeAll
    static void initializeDriver(){ //metodo que setea nuestro driver
        //Donde esta el driver
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver");
        //se inicializa el objeto
        driver = new ChromeDriver();
        //implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //maximizar la ventana con el propósito que el test tenga
        //mayor probabilidad de realizarlo correctamente
        driver.manage().window().maximize();

    }

    //pruebas
    @Test
    void testWikipediaSearch(){
        //obtiene una url, navega al sitio
        driver.get("https://es.wikipedia.org/wiki/Wikipedia:Portada");

        //BUSCAR TEXTO
        WebElement searchBox= driver.findElement(By.cssSelector("#searchInput"));
        String busqueda="gatos";
        //sendKeys es un metodo para enviar texto
        searchBox.sendKeys(busqueda);

        //Hacer clic en el rectangulo
        WebElement buttonSearch=driver.findElement(By.cssSelector("#searchform > div > button"));
        buttonSearch.click();

        //validar
        WebElement tituloTextoBuscado= driver.findElement(By.cssSelector("#firstHeading > span"));
        assertEquals(tituloTextoBuscado.getText(), "Felis silvestris catus");

        //calidando url de busqueda
        assertEquals(driver.getCurrentUrl(), "https://es.wikipedia.org/wiki/Felis_silvestris_catus");

    }

    //para cerrar la clase despues de todas las pruebas
    @AfterAll
    static void closeDriver(){
        driver.close();
        driver.quit();
    }
}