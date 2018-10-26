package com.nxd.ftt.common.util;

import java.math.BigInteger;

/**
 * RightHelper
 *
 * @author luhangqi
 * @date 2018/10/26
 */
public class RightHelper {
    public static void main(String[] args) {
        int[] permissionIds = new int[]{1};
        BigInteger rights = sumRights(permissionIds);
        boolean isHas = testRights(rights, permissionIds[0]);
        System.out.println(rights);
        System.out.println(isHas);
    }

    public static BigInteger sumRights(int[] rights) {
        BigInteger num = new BigInteger("0");
        for (int i = 0; i < rights.length; i++) {
            num = num.setBit(rights[i]);
        }
        return num;
    }

    public static boolean testRights(BigInteger sum, int targetRights) {
        return sum.testBit(targetRights);
    }
}
