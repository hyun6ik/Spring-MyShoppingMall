package hyun6ik.shoppingmall.global.config.database;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Slf4j
public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {

    private SlaveNames slaveNames;

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);

        List<String> replicas = targetDataSources.keySet().stream()
            .map(Object::toString)
            .filter(string -> string.contains("slave"))
            .collect(toList());

        this.slaveNames = new SlaveNames(replicas);
    }

    @Override
    protected String determineCurrentLookupKey() {
        boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        if (isReadOnly) {
            String slaveName = slaveNames.getNextName();

            log.info("Slave DB name: {}", slaveName);

            return slaveName;
        }
        log.info("Master DB");
        return "master";
    }
}
