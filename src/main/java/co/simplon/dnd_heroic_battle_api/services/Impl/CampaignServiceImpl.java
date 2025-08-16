package co.simplon.dnd_heroic_battle_api.services.Impl;

import co.simplon.dnd_heroic_battle_api.config.JwtHelper;
import co.simplon.dnd_heroic_battle_api.dtos.campaign.CampaignCreate;
import co.simplon.dnd_heroic_battle_api.dtos.campaign.CampaignUpdate;
import co.simplon.dnd_heroic_battle_api.mappers.CampaignMapper;
import co.simplon.dnd_heroic_battle_api.models.CampaignModel;
import co.simplon.dnd_heroic_battle_api.repositories.CampaingRepository;
import co.simplon.dnd_heroic_battle_api.services.CampaignService;
import jakarta.transaction.Transactional;
import org.hibernate.ResourceClosedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CampaignServiceImpl implements CampaignService {

    private final CampaingRepository repo;

    public CampaignServiceImpl(CampaingRepository repo) {
        this.repo = repo;
    }

    @Override
    public void create(CampaignCreate input) {
        Long userId = JwtHelper.getSubject();
        repo.save(CampaignMapper.campaignCreateToEntity(input, userId));
    }

    @Override
    public List<CampaignModel> getAllByUserId() {
        Long userId = JwtHelper.getSubject();
        return CampaignMapper.entitiesToCampaignModel(repo.findByUserUserIdOrderByCreationDateDesc(userId));
    }

    @Override
    public CampaignModel getOne(long id) {
        return CampaignMapper
                .entityToCampaignModel(repo.findById(id)
                        .orElseThrow(() -> new ResourceClosedException("Campaign with id = " + id + " does not exist")));
    }

    @Override
    public void deleteOne(long id) {
        repo.deleteById(id);
    }

    @Override
    public void update(CampaignUpdate input) {
        Long userId = JwtHelper.getSubject();
        repo.update(input.campaignId(), input.campaignName(), userId);
    }

}
