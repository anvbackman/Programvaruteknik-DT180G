/**
 * Package that contains the Container class which is a concrete component and is used to store the messages and
 * making sure that the encryption level is of a positive value.
 * The package also contains the Content class which serves as the base component from which the decorative
 * implementations will derive and is used to increase and set the encryption level, but also check that
 * the encryption level matches the SpyMasters encryption level. The class is also responsible for creating the cipher
 * used to encrypt the messages.
 * Package also contains the MessageInterface which holds the getMessage() method which is used to retreive messages.
 */
package com.dt180g.laboration_2.components;