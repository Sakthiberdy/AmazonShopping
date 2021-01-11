package com.testvagrant.tvu.amazonshopping.listeners;

import com.google.inject.Injector;
import com.google.inject.Module;
import com.testvagrant.tvu.amazonshopping.modules.BrowserModule;
import com.testvagrant.tvu.amazonshopping.modules.PageModule;
import com.testvagrant.tvu.amazonshopping.modules.PropertyModule;
import com.testvagrant.tvu.amazonshopping.modules.SiteModule;
import com.testvagrant.tvu.amazonshopping.site.Constants;
import com.testvagrant.tvu.amazonshopping.site.Launcher;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.util.Arrays;
import java.util.List;

public class DriverListener implements ITestListener {


    @Override
    public void onTestStart(ITestResult result) {
        Reporter.log(String.format("Test %s has started", result.getName().toLowerCase()));
        addDivider();
        Injector driverInjector = result.getTestContext().getSuite().getParentInjector().createChildInjector(setupModules());
        result.setAttribute(Constants.DRIVER_INJECTOR, driverInjector);
        result.setAttribute(Constants.PAGE_INJECTOR, driverInjector);
        driverInjector.getInstance(Launcher.class).launch();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        quit(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        quit(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        quit(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        quit(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        quit(result);
    }


    public void quit(ITestResult result) {
        Injector driver = (Injector) result.getAttribute(Constants.DRIVER_INJECTOR);
        WebDriver driverInstance = driver.getInstance(WebDriver.class);
        addDivider();
        Reporter.log(String.format("Test %s has ended",result.getName().toLowerCase()));
        driverInstance.quit();
    }

    private void addDivider() {
        Reporter.log("==================================================");
    }

    private List<Module> setupModules() {
        return Arrays.asList(
                new SiteModule(),
                new PropertyModule(),
                new BrowserModule(),
                new PageModule());
    }


}
