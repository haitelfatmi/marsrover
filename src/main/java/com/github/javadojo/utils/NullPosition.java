/**
 * 
 */
package com.github.javadojo.utils;

/**
 * @author Hamza
 *
 */
public class NullPosition implements AbsractPosition{

  private static final NullPosition INSTANSE = new NullPosition(); 
  
  private NullPosition(){
    
  }
  
  @Override
  public String toString() {
    return " ";
  }

  public static NullPosition getInstanse() {
    return INSTANSE;
  }
  
}
