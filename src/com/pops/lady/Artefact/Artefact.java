package com.pops.lady.Artefact;

import com.pops.lady.Hero.Hero;

import java.io.Serializable;

/**
 * Created by lady.pops on 08.12.2015.
 */
public interface Artefact extends Serializable {
    Artefact getArtefact();
    boolean work(Hero person);
    Integer getDamage();
    String getName();

}
