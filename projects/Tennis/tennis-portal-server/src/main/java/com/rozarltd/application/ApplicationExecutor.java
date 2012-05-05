package com.rozarltd.application;

import com.rozarltd.module.atpwebsite.domain.TennisTournament;
import com.rozarltd.module.atpwebsite.service.AtpWebsiteClient;
import com.rozarltd.module.betfairdata.service.BetfairDataService;
import com.rozarltd.module.betfairdata.strategy.StartingOddsStrategyExecutor;
import com.rozarltd.repository.TennisTournamentRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ApplicationExecutor {

    public static void main(String[] args) {
        Task task = Task.strategy;

        Application application = new Application();
        switch (task) {
            case replicateAtpTournaments:
                application.executeReplicateAtoTournamentsTask();
                break;
            case processAndSaveMarketData:
                application.processAndSaveMarketData();
                break;
            case strategy:
                application.executeStrategy();
                break;
        }
    }

    public static class Application {
        private ClassPathXmlApplicationContext application;

        public Application() {
            application = new ClassPathXmlApplicationContext("spring\\external\\server-context.xml");
        }

        private void executeReplicateAtoTournamentsTask() {
            System.out.println("Executing task: Replicate ATP Tournament");
            AtpWebsiteClient atpWebsiteService = application.getBean(AtpWebsiteClient.class);
            TennisTournamentRepository repository = application.getBean(TennisTournamentRepository.class);

            List<TennisTournament> tournaments = atpWebsiteService.getTournaments();
            System.out.println("tournaments count = " + tournaments.size());

            if(tournaments != null && !tournaments.isEmpty()) {
                repository.save(tournaments);
            }

            System.out.println("Finished, task: Replicate ATP Tournament");
        }

        private void processAndSaveMarketData() {
            System.out.println("Executing task: " + Task.processAndSaveMarketData);
            BetfairDataService betfairDataService = application.getBean(BetfairDataService.class);

            betfairDataService.processAndSave();

            System.out.println("End task: " + Task.processAndSaveMarketData);
        }

        private void executeStrategy() {
            System.out.println("Executing task: " + Task.strategy);
            StartingOddsStrategyExecutor strategy = application.getBean(StartingOddsStrategyExecutor.class);

            strategy.execute();

            System.out.println("End task: " + Task.strategy);
        }
    }



    private static enum Task {
        replicateAtpTournaments,
        processAndSaveMarketData,
        strategy;
    }
}
