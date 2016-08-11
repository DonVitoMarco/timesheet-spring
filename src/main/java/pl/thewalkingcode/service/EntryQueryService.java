package pl.thewalkingcode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.thewalkingcode.model.Entry;
import pl.thewalkingcode.repository.GetEntries;

import java.util.List;


@Repository
public class EntryQueryService implements IEntryQueryService {

    @Autowired
    GetEntries getEntries;

    public List<Entry> getEntries(String username, String startDate, String endDate) {
        return getEntries.getAllEntries();
    }

}