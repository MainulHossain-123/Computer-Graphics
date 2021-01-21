import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;

public class Lab04 implements GLEventListener{
	
	private GLU glu;
	
	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();

        gl.glPushMatrix();
       
        //for 0 digit
        MLPCircle(gl, -40, 40, 5);
        MLPCircle(gl, -30, 39, 5);
        MLPCircle(gl, -20, 35, 5);
        MLPCircle(gl, -13, 29, 5);
        MLPCircle(gl, -6, 22, 5);
        MLPCircle(gl, -2, 13, 5);
        MLPCircle(gl, 0, 3, 5);
        MLPCircle(gl, 0, -7, 5);
        MLPCircle(gl, -4, -17, 5);
        MLPCircle(gl, -9, -25, 5);
        MLPCircle(gl, -16, -32, 5);
        MLPCircle(gl, -25, -37, 5);
        MLPCircle(gl, -35, -40, 5);
        MLPCircle(gl, -45, -40, 5);
        MLPCircle(gl, -54, -37, 5);
        MLPCircle(gl, -63, -32, 5);
        MLPCircle(gl, -71, -26, 5);
        MLPCircle(gl, -76, -17, 5);
        MLPCircle(gl, -79, -8, 5);
        MLPCircle(gl, -80, 2, 5);
        MLPCircle(gl, -78, 12, 5);
        MLPCircle(gl, -74, 21, 5);
        MLPCircle(gl, -68, 29, 5);
        MLPCircle(gl, -60, 35, 5);
        MLPCircle(gl, -50, 39, 5);
        
        //for 3 digit
        //for 3 top head
        MLPCircle(gl, 40, 40, 5);
        MLPCircle(gl, 45, 40, 5);
        MLPCircle(gl, 50, 40, 5);
        MLPCircle(gl, 55, 40, 5);
        MLPCircle(gl, 60, 40, 5);
        MLPCircle(gl, 65, 40, 5);
        MLPCircle(gl, 70, 40, 5);
        MLPCircle(gl, 75, 40, 5);
        MLPCircle(gl, 80, 40, 5);
        MLPCircle(gl, 85, 40, 5);
        //for 3 bottom feet
        MLPCircle(gl, 40, -40, 5);
        MLPCircle(gl, 45, -40, 5);
        MLPCircle(gl, 50, -40, 5);
        MLPCircle(gl, 55, -40, 5);
        MLPCircle(gl, 60, -40, 5);
        MLPCircle(gl, 65, -40, 5);
        MLPCircle(gl, 70, -40, 5);
        MLPCircle(gl, 75, -40, 5);
        MLPCircle(gl, 80, -40, 5);
        MLPCircle(gl, 85, -40, 5);
        //for 3 middle belly
        MLPCircle(gl, 40, 0, 5);
        MLPCircle(gl, 45, 0, 5);
        MLPCircle(gl, 50, 0, 5);
        MLPCircle(gl, 55, 0, 5);
        MLPCircle(gl, 60, 0, 5);
        MLPCircle(gl, 65, 0, 5);
        MLPCircle(gl, 70, 0, 5);
        MLPCircle(gl, 75, 0, 5);
        MLPCircle(gl, 80, 0, 5);
        MLPCircle(gl, 85, 0, 5);
        //for 3 spine
        MLPCircle(gl, 85, 40, 5);
        MLPCircle(gl, 85, 30, 5);
        MLPCircle(gl, 85, 20, 5);
        MLPCircle(gl, 85, 10, 5);
        MLPCircle(gl, 85, 0, 5);
        MLPCircle(gl, 85, -10, 5);
        MLPCircle(gl, 85, -20, 5);
        MLPCircle(gl, 85, -30, 5);
        MLPCircle(gl, 85, -40, 5);
        
        
        

		
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
	private void MLPCircle(GL2 gl, int x1 , int y1 ,int r) { // (x1,y1) - centre of circle and r - is radius

        gl.glPointSize(3.0f);
        gl.glColor3d(1, 0, 0);

        int x = 0;
        int y = r;
        int d = 1 - r;
        
        draw8Way(gl , x , y , x1 , y1);
        
        while (y > x) {
            if (d < 0) { //E is chosen
            	d = d + ((2*x)+3);
            	x++;
            }
            
            else { //SE is chosen
                d = d + ((2*x)-(2*y)+5);
                x++;
            	y--;
            }
        
            draw8Way(gl , x , y , x1 , y1);
            
            
        }
    }
    
    
    
    private void draw8Way(GL2 gl, int x, int y, int x1, int y1) {
        gl.glBegin(GL2.GL_POINTS);
        gl.glVertex2d(x+x1, y+y1);
        gl.glVertex2d(y+x1, x+y1);

        gl.glVertex2d(-x+x1, y+y1);
        gl.glVertex2d(-y+x1, x+y1);

        gl.glVertex2d(-x+x1, -y+y1);
        gl.glVertex2d(-y+x1, -x+y1);


        gl.glVertex2d(x+x1, -y+y1);
        gl.glVertex2d(y+x1, -x+y1);
        gl.glEnd();

    }

	public static void main(String[] args) {
		//getting the capabilities object of GL2 profile
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		// The canvas 
		final GLCanvas glcanvas = new GLCanvas(capabilities);
		Lab04 l = new Lab04();
		glcanvas.addGLEventListener(l);
		glcanvas.setSize(400, 400);
		//creating frame
		final JFrame frame = new JFrame ("Line Algorithm: Mid Point Circle");
		//adding canvas to frame
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
	}  //end of main
}  //end of classimport javax.media.opengl.GL2; 