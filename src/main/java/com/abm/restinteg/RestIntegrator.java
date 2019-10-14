package com.abm.restinteg;

import com.abm.restinteg.core.ConfigFileLoader;
import com.abm.restinteg.core.RestIntegBeansConfig;
import com.abm.restinteg.core.TestCaseRunner;

public class RestIntegrator {

    private TestCaseRunner testCaseRunner;
    private RestIntegBeansConfig restIntegBeansConfig;

    public RestIntegrator() throws Exception {
        init();
    }

    private void init() {
        ConfigFileLoader configFileLoader = new ConfigFileLoader();
        restIntegBeansConfig = new RestIntegBeansConfig(configFileLoader);
        testCaseRunner = restIntegBeansConfig.configure();
        try {
            testCaseRunner.invokeTests();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        new RestIntegrator();
    }

}
