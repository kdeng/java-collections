package nz.net.osnz.demos.spi.impl;

import nz.net.osnz.demos.spi.helper.AddHelper;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
public class OneAddHelper implements AddHelper {

    @Override
    public int add(int a, int b) {
        return a + b + 1;
    }
}
