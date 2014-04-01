package com.senacrs.BatalhaNaval;

import com.senacrs.BatalhaNaval.controller.BatalhaNavalController;
import com.senacrs.BatalhaNaval.view.BatalhaNavalView;

public class BatalhaNaval {
	public static void main(String[] args) {
		BatalhaNavalController controller = new BatalhaNavalController();
		BatalhaNavalView view = new BatalhaNavalView();
		controller.iniciar(view);
		controller.jogar();
	}	
}
