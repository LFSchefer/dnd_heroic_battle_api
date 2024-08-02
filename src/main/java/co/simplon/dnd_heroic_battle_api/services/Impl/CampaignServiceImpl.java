package co.simplon.dnd_heroic_battle_api.services.Impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.dnd_heroic_battle_api.dtos.campaign.CampaignCreate;
import co.simplon.dnd_heroic_battle_api.dtos.campaign.CampaignUpdate;
import co.simplon.dnd_heroic_battle_api.dtos.campaign.CampaignView;
import co.simplon.dnd_heroic_battle_api.mappers.CampaignMapper;
import co.simplon.dnd_heroic_battle_api.repositories.CampaingRepository;
import co.simplon.dnd_heroic_battle_api.services.CampaignService;

@Service
public class CampaignServiceImpl implements CampaignService {

    private final CampaingRepository repo;

    public CampaignServiceImpl(CampaingRepository repo) {
	this.repo = repo;
    }

    @Transactional
    @Override
    public void create(CampaignCreate input) {
	repo.save(CampaignMapper.campaignCreateToEntity(input));
    }

    @Transactional(readOnly = true)
    @Override
    public List<CampaignView> getAll() {
	return CampaignMapper.entitiesToCampaignViews(repo.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public CampaignView getOne(long id) {
	return CampaignMapper.entityToCampaignView(repo.findById(id)
		.orElseThrow(() -> new IllegalArgumentException("Campaign with id = " + id + " does not exist")));
    }

    @Transactional
    @Override
    public void deleteOne(long id) {
	repo.deleteById(id);
    }

    @Transactional
    @Override
    public void update(CampaignUpdate input) {
	repo.update(input.id(), input.campaignName());
    }

}
