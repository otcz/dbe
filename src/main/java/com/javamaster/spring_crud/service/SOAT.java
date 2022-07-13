package com.javamaster.spring_crud.service;


import com.javamaster.spring_crud.modelo.Vehiculo;
import lombok.Getter;
import lombok.Setter;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SOAT {
    @Getter
    @Setter
    private Vehiculo vehiculo;


    public SOAT(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }



    public byte[] generarSOAT()  {
        try {
            List<Vehiculo> vehiculos = Arrays.asList(getVehiculo());
            Map<String, Object> parameters = new HashMap();
            parameters.put("telefono", getVehiculo().getTelefono());
            parameters.put("nonewsoat", getVehiculo().getNonewsoat());
            parameters.put("placa", getVehiculo().getPlaca());
            parameters.put("clase", getVehiculo().getClase());
            parameters.put("tipo", getVehiculo().getTipo());
            parameters.put("cilindraje", getVehiculo().getCilindraje());
            parameters.put("modelo", getVehiculo().getModelo());
            parameters.put("ocupantes", getVehiculo().getOcupantes());
            parameters.put("marca", getVehiculo().getMarca());
            parameters.put("linea", getVehiculo().getLinea());
            parameters.put("nomotor", getVehiculo().getNomotor());
            parameters.put("nochasis", getVehiculo().getNochasis());
            parameters.put("toneladas", getVehiculo().getToneladas());
            parameters.put("nombres", getVehiculo().getNombres());
            parameters.put("identificacion", getVehiculo().getIdentificacion());
            parameters.put("yyycomsoat", getVehiculo().getYyycomsoat());
            parameters.put("mmcomsoat", getVehiculo().getMmcomsoat());
            parameters.put("ddcomsoat", getVehiculo().getDdcomsoat());
            parameters.put("yyyvennusoat", getVehiculo().getYyyvennusoat());
            parameters.put("mmvennusoat", getVehiculo().getMmvennusoat());
            parameters.put("ddvennusoat", getVehiculo().getDdvennusoat());
            parameters.put("valnewsoat", getVehiculo().getValnewsoat());


            InputStream is = new FileInputStream(new File("src/main/java/TaosBackend/Taos/Utils/soatV2.jrxml"));

            JasperReport report = null;

            report = JasperCompileManager.compileReport(is);

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(vehiculos);
            JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);
           return JasperExportManager.exportReportToPdf(print);

        } catch (JRException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
