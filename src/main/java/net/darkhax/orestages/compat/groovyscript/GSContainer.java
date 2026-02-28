package net.darkhax.orestages.compat.groovyscript;

import com.cleanroommc.groovyscript.compat.mods.GroovyPropertyContainer;

public class GSContainer extends GroovyPropertyContainer {
    public static final GSOreStages oreStages = new GSOreStages();

    public GSContainer() {
        this.addProperty(oreStages);
    }
}
