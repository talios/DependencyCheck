/*
 * This file is part of dependency-check-core.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) 2013 Jeremy Long. All Rights Reserved.
 */
package org.owasp.dependencycheck.data.update;

import java.net.MalformedURLException;
import java.util.Calendar;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.owasp.dependencycheck.BaseTest;
import org.owasp.dependencycheck.data.update.exception.UpdateException;
import org.owasp.dependencycheck.utils.DownloadFailedException;

/**
 *
 * @author Jeremy Long <jeremy.long@owasp.org>
 */
public class StandardUpdateIntegrationTest extends BaseTest {

    public StandardUpdate getStandardUpdateTask() throws MalformedURLException, DownloadFailedException, UpdateException {
        StandardUpdate instance = new StandardUpdate();
        return instance;
    }

    /**
     * Test of openDataStores method, of class StandardUpdate.
     */
    @Test
    public void testOpenDataStores() throws Exception {
        StandardUpdate instance = getStandardUpdateTask();
        instance.openDataStores();
        instance.closeDataStores();
    }

    /**
     * Test of withinRange method, of class StandardUpdate.
     */
    @Test
    public void testWithinRange() throws Exception {
        Calendar c = Calendar.getInstance();

        long current = c.getTimeInMillis();
        long lastRun = c.getTimeInMillis() - (3 * (1000 * 60 * 60 * 24));
        int range = 7; // 7 days
        StandardUpdate instance = getStandardUpdateTask();
        boolean expResult = true;
        boolean result = instance.withinRange(lastRun, current, range);
        assertEquals(expResult, result);

        lastRun = c.getTimeInMillis() - (8 * (1000 * 60 * 60 * 24));
        expResult = false;
        result = instance.withinRange(lastRun, current, range);
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class StandardUpdate.
     */
    @Test
    public void testUpdate() throws Exception {
        StandardUpdate instance = getStandardUpdateTask();
        instance.update();
        //TODO make this an actual test
    }

    /**
     * Test of updatesNeeded method, of class StandardUpdate.
     */
    @Test
    public void testUpdatesNeeded() throws Exception {
        StandardUpdate instance = getStandardUpdateTask();
        UpdateableNvdCve result = instance.updatesNeeded();
        assertNotNull(result);
    }
}
