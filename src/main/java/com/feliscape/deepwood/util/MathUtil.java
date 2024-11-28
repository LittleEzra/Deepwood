package com.feliscape.deepwood.util;

import net.minecraft.util.Mth;

public class MathUtil {
    /**
     * The simplified damped sine wave as explained in <a href="https://www.statisticshowto.com/calculus-definitions/damped-sine-wave/">Damped Sine Wave: Definition, Example, Formula</a>
     * @param initialPeak (also A) the value of the function at the start
     * @param dampeningCoefficient (also λ/lambda) the rate at which the function decays
     * @param phaseAngle (also Φ/phi) controls where the start of the function is
     * @param angularFrequency (also ω/omega) controls the frequency of the wave
     */
    public static float dampedSin(float initialPeak, float dampeningCoefficient, float phaseAngle, float angularFrequency, float t){
        return (float) (initialPeak * Math.pow(Math.E, -dampeningCoefficient * t) * Mth.cos(angularFrequency * t + phaseAngle));
    }
}
