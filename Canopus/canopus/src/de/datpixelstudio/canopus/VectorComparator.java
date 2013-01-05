package de.datpixelstudio.canopus;

import java.util.Comparator;

import com.badlogic.gdx.math.Vector3;

public class VectorComparator implements Comparator<Vector3>  {
	
    public VectorComparator() {
        
    }
    
    @Override
    public int compare(Vector3 o1, Vector3 o2) {
        double angle1 = Math.atan2(o1.y , o1.x);
        double angle2 = Math.atan2(o2.y , o2.x);

        if(angle1 >= angle2) return 1;
        else if (angle2 <= angle1) return -1;
        return 0;
    }

}