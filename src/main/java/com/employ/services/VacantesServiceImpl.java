package com.employ.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.employ.controller.copy.model.model.*;

@Service
public class VacantesServiceImpl implements IVacantesService {

    private List<Vacantes> lista;

    public VacantesServiceImpl(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		lista = new LinkedList<Vacantes>();
		try {
			Vacantes vacante1 = new Vacantes();
			vacante1.setId(1);
			vacante1.setName("Ingeniero en Sistemas");
			vacante1.setDescripcion("SI");
			vacante1.setFecha(sdf.parse("07-04-2002"));
			vacante1.setSalario(1200.0);
			vacante1.setDestacado(1);
			vacante1.setImages("logo2.png");

			Vacantes vacante2 = new Vacantes();
			vacante2.setId(2);
			vacante2.setName("Ingeniero en Sistemas 2");
			vacante2.setDescripcion("SI");
			vacante2.setFecha(sdf.parse("08-05-2022"));
			vacante2.setSalario(1200.0);
			vacante2.setDestacado(0);
			vacante2.setImages("logo3.png");
			
			Vacantes vacante3 = new Vacantes();
			vacante3.setId(3);
			vacante3.setName("Ingeniero en Sistemas 3");
			vacante3.setDescripcion("SI");
			vacante3.setFecha(sdf.parse("08-06-2022"));
			vacante3.setSalario(1200.0);
			vacante3.setDestacado(0);

			Vacantes vacante4 = new Vacantes();
			vacante4.setId(4);
			vacante4.setName("Ingeniero en Sistemas 4");
			vacante4.setDescripcion("SI");
			vacante4.setFecha(sdf.parse("08-08-2022"));
			vacante4.setSalario(1200.0);
			vacante4.setDestacado(1);
			vacante4.setImages("logo4.png");

			lista.add(vacante1);
			lista.add(vacante2);
			lista.add(vacante3);
			lista.add(vacante4);

		} catch(ParseException e) {
			System.out.println("Error: "+ e.getMessage());
		}
    }

    public List<Vacantes> buscarTodas() {
        return lista;
    }

    public Vacantes buscarPorId(Integer idVacante) {
        for (Vacantes v: lista){
            if(v.getId() == idVacante){
                return v;
            }
        }
        return null;
    }

	public void guardar(Vacantes vacantes) {
		lista.add(vacantes);
	}

	@Override
	public List<Vacantes> buscarDestacadas() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'buscarDestacadas'");
	}

	@Override
	public void eliminar(Integer idVacante) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
	}

	@Override
	public List<Vacantes> buscarByExample(Example<Vacantes> examples) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'buscarByExample'");
	}

	@Override
	public Page<Vacantes> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'buscarTodas'");
	}
    
}
