package ar.com.bamboo.commons.fiscalUtils

import groovy.transform.CompileStatic

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

/**
 * Created by orko on 13/08/14.
 */
@CompileStatic
class CondicionIva {

    public static final Integer RESPONSABLE_INSCRIPTO = 1;
    public static final Integer EXENTO = 2;
    public static final Integer MONOTRIBUTISTA = 3;
    public static final Integer MONOTRIBUTO_SOCIAL = 4;
    public static final Integer CONSUMIDOR_FINAL = 5;

    public static ConcurrentMap<Integer, String> nombreCondicionIva =
            new ConcurrentHashMap<Integer, String>((int)Math.ceil(5.00 / 0.75) + 1);

    static{
        nombreCondicionIva.put(RESPONSABLE_INSCRIPTO, "Responsable Inscripto");
        nombreCondicionIva.put(EXENTO, "Exento");
        nombreCondicionIva.put(MONOTRIBUTISTA, "Monotributista");
        nombreCondicionIva.put(MONOTRIBUTO_SOCIAL, "Monotributista Social");
        nombreCondicionIva.put(CONSUMIDOR_FINAL, "Consumidor Final");
    }
}
