package net.darkhax.orestages.compat.groovyscript;

import com.cleanroommc.groovyscript.documentation.linkgenerator.BasicLinkGenerator;
import net.darkhax.orestages.OreStages;

public class GSLinkGenerator extends BasicLinkGenerator {
    @Override
    public String id() {
        return OreStages.MOD_ID;
    }

    @Override
    protected String domain() {
        return "https://github.com/Invadermonky/OreStagesReStaged/";
    }

    @Override
    protected String version() {
        return OreStages.MOD_VERSION;
    }
}
