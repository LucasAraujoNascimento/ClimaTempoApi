package com.nascimento.api_clima.controllers;

import com.nascimento.api_clima.models.HistoricoModel;
import com.nascimento.api_clima.repositories.HistoricoRepository;
import com.nascimento.api_clima.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class HistoricoController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private HistoricoRepository historicoRepository;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/ConsultaClima")
    public String consultarClima(@RequestParam("cidade") String cidade, Model model) {
        var weatherResponse = weatherService.getWeather(cidade);

        if (weatherResponse == null || weatherResponse.getCurrent() == null) {
            model.addAttribute("error", "Erro ao obter dados de clima. Tente novamente mais tarde.");
            return "consultaClima";
        }

        String dataHora = weatherResponse.getCurrent().getLastUpdated();

        model.addAttribute("cidade", weatherResponse.getLocation().getName());
        model.addAttribute("temperatura", weatherResponse.getCurrent().getTempC());
        model.addAttribute("descricao", weatherResponse.getCurrent().getCondition().getText());
        model.addAttribute("dataHora", dataHora);

        return "consultaClima";
    }

    @PostMapping("/SalvarBusca")
    public String salvarBusca(@RequestParam("cidade") String cidade,
                              @RequestParam("temperatura") String temperatura,
                              @RequestParam("descricao") String descricao,
                              @RequestParam("dataHora") String dataHoraStr, Model model) {

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date parsedDate = dateFormat.parse(dataHoraStr);
            Timestamp timestamp = new Timestamp(parsedDate.getTime());

            HistoricoModel historico = new HistoricoModel();
            historico.setCidade(cidade);
            historico.setTemperatura(temperatura);
            historico.setDescricao(descricao);
            historico.setDataHora(timestamp);

            historicoRepository.save(historico);

            String formattedDataHora = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(timestamp);
            model.addAttribute("formattedDataHora", formattedDataHora);

            return "redirect:/historico";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Erro ao salvar a busca no histórico: " + e.getMessage());
            return "consultaClima";
        }
    }


    @GetMapping("/historico")
    public String listarHistorico(Model model) {
        List<HistoricoModel> listaHistorico = historicoRepository.findAll();
        model.addAttribute("listaHistorico", listaHistorico);
        return "historico";
    }
}
