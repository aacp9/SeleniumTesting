package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        //Donde esta el driver
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver");

        //Crea el objeto del driver
        WebDriver driver = new ChromeDriver();

        //implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //obtiene una url, navega al sitio
        driver.get("https://es.wikipedia.org/wiki/Wikipedia:Portada");

        //SELECTORES
        //Por ID <- seleccion más rápida
        //revisando tiempo de ejecucion
        long start= System.currentTimeMillis();
        String texto = driver.findElement(By.id("Artículo_destacado")).getText();
        System.out.println("Texto leido de Wikipedia por ID: "+texto);
        long stop= System.currentTimeMillis();
        System.out.println("tiempo para ID: "+(stop-start));

        //revisando tiempo de ejecucion
        start= System.currentTimeMillis();
        //seleccion por XPath <- para cualquier elemento del DOM
        texto=driver.findElement(By.xpath("//*[@id=\"Artículo_destacado\"]")).getText();
        System.out.println("Texto leido de Wikipedia por XPATH: "+texto);
        stop= System.currentTimeMillis();
        System.out.println("tiempo para xpath: "+(stop-start));

        //revisando tiempo de ejecucion
        start= System.currentTimeMillis();
        //Selectores CSS <- para cualquier elemento
        texto=driver.findElement(By.cssSelector("#Artículo_destacado")).getText();
        System.out.println("Texto leido de Wikipedia por CSS: "+texto);
        stop= System.currentTimeMillis();
        System.out.println("tiempo para CSS: "+(stop-start));

        //Selectores CSS <- para cualquier elemento, quiero mi "café"
        texto=driver.findElement(By.cssSelector("#mw-content-text > div.mw-content-ltr.mw-parser-output > div.main-top > div.main-top-right.main-plainlist > ul > li:nth-child(1) > a > span")).getText();
        System.out.println("Texto leido 'Café' de Wikipedia por CSS: "+texto);

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
        if (tituloTextoBuscado.getText().equals("Felis silvestris catus")){
            System.out.println("busqueda exitosa");
        }else{
            System.out.println("No encontro lo que buscabamos");
        }

        //cierra el driver
        driver.close();
        driver.quit();
    }
}