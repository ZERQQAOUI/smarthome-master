/**
 * Copyright (c) 2014,2019 Contributors to the Eclipse Foundation
v2

 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.smarthome.binding.astro.handler;

import static org.eclipse.smarthome.binding.astro.AstroBindingConstants.THING_TYPE_MOON;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.smarthome.binding.astro.internal.calc.MoonCalc;
import org.eclipse.smarthome.binding.astro.internal.job.DailyJobMoon;
import org.eclipse.smarthome.binding.astro.internal.job.Job;
import org.eclipse.smarthome.binding.astro.internal.model.Moon;
import org.eclipse.smarthome.binding.astro.internal.model.Planet;
import org.eclipse.smarthome.core.scheduler.CronScheduler;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingTypeUID;

/**
 * The MoonHandler is responsible for updating calculated moon data.
 *
 * @author Gerhard Riegler - Initial contribution
 * @author Amit Kumar Mondal - Implementation to be compliant with ESH Scheduler
 */
public class MoonHandler extends AstroThingHandler {

    public static final Set<ThingTypeUID> SUPPORTED_THING_TYPES = new HashSet<>(Arrays.asList(THING_TYPE_MOON));

    private final String[] positionalChannelIds = new String[] { "phase#name", "phase#age", "phase#agePercent",
            "phase#ageDegree", "phase#illumination", "position#azimuth", "position#elevation", "zodiac#sign" };
    private final MoonCalc moonCalc = new MoonCalc();
    private Moon moon;

    /**
     * Constructor
     */
    public MoonHandler(Thing thing, CronScheduler scheduler) {
        super(thing, scheduler);
    }

    @Override
    public void publishDailyInfo() {
        initializeMoon();
        publishPositionalInfo();
    }

    @Override
    public void publishPositionalInfo() {
        initializeMoon();
        moonCalc.setPositionalInfo(Calendar.getInstance(), thingConfig.getLatitude(), thingConfig.getLongitude(), moon);
        publishPlanet();
    }

    @Override
    public Planet getPlanet() {
        return moon;
    }

    @Override
    public void dispose() {
        super.dispose();
        moon = null;
    }

    @Override
    protected String[] getPositionalChannelIds() {
        return positionalChannelIds;
    }

    @Override
    protected Job getDailyJob() {
        return new DailyJobMoon(thing.getUID().getAsString(), this);
    }

    private void initializeMoon() {
        moon = moonCalc.getMoonInfo(Calendar.getInstance(), thingConfig.getLatitude(), thingConfig.getLongitude());
    }

}
