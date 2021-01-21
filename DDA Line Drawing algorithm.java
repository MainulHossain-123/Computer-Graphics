import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;

public class Lab02 implements GLEventListener{

	private GLU glu;
	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();

		DDA(gl, -10,20,-10,-20);
		DDA(gl, -10,20,10,20);
		DDA(gl, 10,20,10,-20);
		DDA(gl, -10,-20,10,-20);
		DDA(gl, 30,20,50,20);
		DDA(gl, 50,20,50,-20);
		DDA(gl, 30,-20,50,-20);
		DDA(gl, 30,0,50,0);

	}
	@Override
	public void dispose(GLAutoDrawable arg0) {
		//method body
	}

	@Override
	public void init(GLAutoDrawable gld) {
		GL2 gl = gld.getGL().getGL2();
		glu = new GLU();

		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glViewport(-100, -50, 50, 100);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluOrtho2D(-100.0, 100.0, -100.0, 100.0);
	}



	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// method body
	}

	/* Edited */
	public void DDA(GL2 gl, float x1, float y1, float x2, float y2) {
        gl.glPointSize(3.0f);
        gl.glColor3d(1, 0, 0);
        // code here
        
        
        float dx = x2-x1;
        float dy = y2-y1;
        float m = dy/dx;

        int pixels; //Based on the calculated difference number of pixels to put 
        if(Math.abs(dx) > Math.abs(dy)) {
            pixels = (int) Math.round(Math.abs(dx));
        }
        else{
            pixels = (int) Math.round(Math.abs(dy)); 
        }
        
        //setting up the increment variables, FYI this would be so mucb easier if we used double.
        float x_inc = 0; 
        float y_inc = 0;
        float x = x1;
        float y = y1;
        
        gl.glBegin(GL2.GL_POINTS); // Plotting of lines begins here
        gl.glVertex2f(x, y);

        if(Math.abs(m)<= 1 && Math.abs(m)>= -1) { //if (-1<= m <=1) we increment y by m
            if(x2 >= x1) { //if lines needs to go above x axis
              x_inc = 1;
            }
            
            else { // if lines needs to go below x axis
              x_inc = -1;
            }
            
            if(y2 >= y1) { //if lines extends to positive y axis
              y_inc = (Math.abs(m));
            }
            
            else { // if lines extends to negative y axis
              y_inc = -1*(Math.abs(m));
            }
            
        }
        
        // in case when the value of m is out of bounds of -1< m >1, we increment x by 1/m
        else { 
            if(y2 >= y1) { //if lines extends to positive y axis
              y_inc = 1;
            }
            else {// if lines extends to negative y axis
              y_inc = -1;
            }
            if(x2 >= x1) {//if lines needs to go above x axis
              x_inc = (1/Math.abs(m));
            }
            else {// if lines needs to go below x axis
              x_inc = -1*(1/Math.abs(m));
            }
        }
//all calculated pixels placed in neccessary order by loop by rounding off.
        int i=1;
        while (i<=pixels) {
            x = x+x_inc;
            y = y+y_inc;
            gl.glVertex2f((int) Math.round(x), (int) Math.round(y));
            i++;
        }

        gl.glEnd(); //end of plotting graph
    }


	public static void main(String[] args) {
		//getting the capabilities object of GL2 profile
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		// The canvas 
		final GLCanvas glcanvas = new GLCanvas(capabilities);
		Lab02 l = new Lab02();
		glcanvas.addGLEventListener(l);
		glcanvas.setSize(400, 400);
		//creating frame
		final JFrame frame = new JFrame ("Line Algorithm: DDA");
		//adding canvas to frame
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
	}  //end of main
}  //end of classimport javax.media.opengl.GL2; 