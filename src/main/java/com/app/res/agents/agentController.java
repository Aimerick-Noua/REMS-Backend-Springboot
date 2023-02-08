package com.app.res.agents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class agentController {
private final agentService agentservice;
private final agentRepository agentrepository;
@Autowired
    public agentController(agentService agentservice, agentRepository agentrepository) {
        this.agentservice = agentservice;
    this.agentrepository = agentrepository;
}
    @GetMapping(path = "/agents")
    public List<agentClass> getAgent() {
      return agentservice.getAgents();
    }
    @PostMapping(path = "/agents")
    public void registerAgent(@RequestBody agentClass agentclass){
         agentservice.addNewAgent(agentclass);
    }
    @DeleteMapping(path = "/agents/{agentId}")
    public void deleteStudent(@PathVariable ("agentId") Long agentId){
         agentservice.deleteAgent(agentId);
    }
    @PutMapping(path="/agents/{agentId}")
    public ResponseEntity<agentClass> updateAgent(@PathVariable(value = "agentId") Long agentId, @RequestBody agentClass new_agent) {
        agentClass agent = agentrepository.findById(agentId).orElseThrow(()->new IllegalStateException("error while attempting to delete data"));
        agent.setFirstName(new_agent.getFirstName());
        agent.setLastName(new_agent.getLastName());
        agent.setEmail(new_agent.getEmail());
        agent.setDob(new_agent.getDob());
        agent.setUserName(new_agent.getUserName());
        agent.setPassword(new_agent.getPassword());
        agent.setSex(new_agent.getSex());
        agent.setTelNum(new_agent.getTelNum());
        agent.setAddress(new_agent.getAddress());
        agent.setNationality(new_agent.getNationality());
        agent.setMarital_status(new_agent.getMarital_status());
        final agentClass agent_new = agentrepository.save(agent);
        return ResponseEntity.ok(agent_new);
    }


}
