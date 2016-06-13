
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.*;
import model.*;


/**
 * Servlet implementation class search
 */
@WebServlet("/search")
public class search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		System.out.println("已到search.java");
		PrintWriter out = response.getWriter();
		JSONArray ja = new JSONArray();

		//初始化库存	
		initializeInventory();

		Builder builder = Builder.valueOf(request.getParameter("Builder").toUpperCase());
		Type type = Type.valueOf(request.getParameter("Type").toUpperCase());
		Wood backWood = Wood.valueOf(request.getParameter("BackWood").toUpperCase());
		Wood topWood = Wood.valueOf(request.getParameter("TopWood").toUpperCase());
		int numStrings = Integer.parseInt(request.getParameter("numStrings"));
		String model = request.getParameter("Model");
		/*Builder builder = Builder.valueOf("FENDER");
		Type type = Type.valueOf("ACOUSTIC");
		Wood backWood = Wood.valueOf("ALDER");
		Wood topWood = Wood.valueOf("ALDER");
		int numStrings = 6;
		String model = "CJ";*/
		GuitarSpec userSpec = new GuitarSpec(builder, model, type, numStrings, backWood, topWood);

		// 查询匹配
		List<Guitar> Guitars = Inventory.search(userSpec);

		if (!Guitars.isEmpty()) {
			for (Iterator<Guitar> i = Guitars.iterator(); i.hasNext();) {
				Guitar guitar = (Guitar) i.next();
				GuitarSpec spec = guitar.getSpec();
				// 写入json
				JSONObject jo = new JSONObject();
				jo.put("serialNumber", guitar.getSerialNumber());
				jo.put("price", guitar.getPrice());
				jo.put("builder", spec.getBuilder().toString());
				jo.put("model", spec.getModel());
				jo.put("type", spec.getType().toString());
				jo.put("backwood", spec.getBackWood().toString());
				jo.put("topwood", spec.getTopWood().toString());
				jo.put("numStrings", spec.getnumStrings());
				ja.put(jo);
			}
		}
		out.print(ja.toString());
		out.close();
	}

	private static void initializeInventory(){
	//面向接口：
		IGuitarDao Load = UsingDB.createGuitarDao();
		
	try {
		boolean load= Load.LoadGuitars();
		System.out.print(load);	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
