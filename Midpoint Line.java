import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;

public class Lab03 implements GLEventListener{

	private GLU glu;
	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();

		MPL(gl, -10,20,-11,-20);
		MPL(gl, -10,20,10,20);
		MPL(gl, 10,20,11,-20);
		MPL(gl, -10,-20,10,-20);
		MPL(gl, 30,20,50,20);
		MPL(gl, 50,20,51,-20);
		MPL(gl, 30,-20,50,-20);
		MPL(gl, 30,0,50,0);
		

		
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
	public void MPL(GL2 gl, int x1, int y1, int x2, int y2) {
        gl.glPointSize(3.0f);
        gl.glColor3d(1, 0, 0);
        // code here
        
        int zone = findZone(x1, y1, x2, y2);


        int convertedZones[] = convertZone0(zone, x1, y1, x2, y2);

        x1 = convertedZones[0];
        y1 = convertedZones[1];
        x2 = convertedZones[2];
        y2 = convertedZones[3];

       
        int dxUpdated = convertedZones[2] - convertedZones[0];
        int dyUpdated = convertedZones[3] - convertedZones[1];

        int d = 2 * dyUpdated - dxUpdated;
        int nE = 2 * (dyUpdated - dxUpdated);
        int e = 2 * dyUpdated;
        
        
        int x = x1;
        int y = y1;

        while (x <= x2) {
            int[] zone0toOriginal = zone0toOriginal(zone, x, y);
            
            gl.glBegin(GL2.GL_POINTS);
            gl.glVertex2d(zone0toOriginal[0], zone0toOriginal[1]);
            gl.glEnd();

            x++;
            if (d > 0) {
                y++;
                d = d + nE;
            }

            else {
                d = d + e;
            }

        }
    }

	int findZone(int x1, int y1, int x2, int y2) {
        int zone = 0;
        int dy = y2 - y1;
        int dx = x2 - x1;

        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0 && dy > 0) {
                zone = 0;
            }

            else if (dx < 0 && dy > 0) {
                zone = 3;
            }

            else if (dx < 0 && dy < 0) {
                zone = 4;
            }

            else if (dx > 0 && dy < 0) {
                zone = 7;
            }

        }

        else {
            if (dx > 0 && dy > 0) {
                zone = 1;
            }

            else if (dx < 0 && dy > 0) {
                zone = 2;
            }

            else if (dx < 0 && dy < 0) {
                zone = 5;
            }

            else if (dx > 0 && dy < 0) {
                zone = 6;
            }
        }

        return zone;
    }

    int[] convertZone0(int zone, int x1, int y1, int x2, int y2) {
        int[] convertedZones = new int[4];
        
        switch (zone) {
            case 0:
                convertedZones[0] = x1;
                convertedZones[1] = y1;
                convertedZones[2] = x2;
                convertedZones[3] = y2;
                break;

            case 1:
                convertedZones[0] = y1;
                convertedZones[1] = x1;
                convertedZones[2] = y2;
                convertedZones[3] = x2;
                break;

            case 2:
                convertedZones[0] = y1;
                convertedZones[1] = -1 * x1;
                convertedZones[2] = y2;
                convertedZones[3] = -1 * x2;
                break;

            case 3:
                convertedZones[0] = -1 * x1;
                convertedZones[1] = y1;
                convertedZones[2] = -1 * x2;
                convertedZones[3] = y2;
                break;
            case 4:
                convertedZones[0] = -1 * x1;
                convertedZones[1] = -1 * y1;
                convertedZones[2] = -1 * x2;
                convertedZones[3] = -1 * y2;
                break;
            case 5:
                convertedZones[0] = -1 * y1;
                convertedZones[1] = -1 * x1;
                convertedZones[2] = -1 * y2;
                convertedZones[3] = -1 * x2;
                break;
            case 6:
                convertedZones[0] = -1 * y1;
                convertedZones[1] = x1;
                convertedZones[2] = -1 * y2;
                convertedZones[3] = x2;
                break;
            case 7:
                convertedZones[0] = x1;
                convertedZones[1] = -1 * y1;
                convertedZones[2] = x2;
                convertedZones[3] = -1 * y2;
                break;
            default:
                break;
        }
        
        return convertedZones;

    }

    int[] zone0toOriginal(int zone, int x1, int y1) {
        int[] zone0toOriginal = new int[2];

        switch (zone) {
            case 0:
                zone0toOriginal[0] = x1;
                zone0toOriginal[1] = y1;
                break;

            case 1:
                zone0toOriginal[0] = y1;
                zone0toOriginal[1] = x1;
                break;

            case 2:
                zone0toOriginal[0] = -1 * y1;
                zone0toOriginal[1] = x1;
                break;

            case 3:
                zone0toOriginal[0] = -1 * x1;
                zone0toOriginal[1] = y1;
                break;
            case 4:
                zone0toOriginal[0] = -1 * x1;
                zone0toOriginal[1] = -1 * y1;
                break;
            case 5:
                zone0toOriginal[0] = -1 * y1;
                zone0toOriginal[1] = -1 * x1;
                break;
            case 6:
                zone0toOriginal[0] = y1;
                zone0toOriginal[1] = -1 * x1;
                break;
            case 7:
                zone0toOriginal[0] = x1;
                zone0toOriginal[1] = -1 * y1;
                break;
            default:
                break;
        }

        return zone0toOriginal;

    }


	public static void main(String[] args) {
		//getting the capabilities object of GL2 profile
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		// The canvas 
		final GLCanvas glcanvas = new GLCanvas(capabilities);
		Lab03 l = new Lab03();
		glcanvas.addGLEventListener(l);
		glcanvas.setSize(400, 400);
		//creating frame
		final JFrame frame = new JFrame ("Line Algorithm: Mid Point Line");
		//adding canvas to frame
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
	}  //end of main
}  //end of classimport javax.media.opengl.GL2; 