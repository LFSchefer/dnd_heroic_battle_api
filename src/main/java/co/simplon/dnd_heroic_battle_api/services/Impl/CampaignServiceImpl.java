package co.simplon.dnd_heroic_battle_api.services.Impl;

import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.dnd_heroic_battle_api.config.JwtUtils;
import co.simplon.dnd_heroic_battle_api.dtos.campaign.CampaignCreate;
import co.simplon.dnd_heroic_battle_api.dtos.campaign.CampaignUpdate;
import co.simplon.dnd_heroic_battle_api.mappers.CampaignMapper;
import co.simplon.dnd_heroic_battle_api.models.CampaignModel;
import co.simplon.dnd_heroic_battle_api.repositories.CampaingRepository;
import co.simplon.dnd_heroic_battle_api.services.CampaignService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CampaignServiceImpl implements CampaignService {

	private final CampaingRepository repo;
	private final JwtUtils jwtUtils;


	@Transactional
	@Override
	public void create(CampaignCreate input, String token) {
		String userId = jwtUtils.getSubject(token);
		repo.save(CampaignMapper.campaignCreateToEntity(input, userId));
	}

	@Transactional(readOnly = true)
	@Override
	public List<CampaignModel> getAllByUserId(String token) {
		String userId = jwtUtils.getSubject(token);
		return CampaignMapper.entitiesToCampaignModel(repo.findByUserUserId(Long.valueOf(userId)));
	}

	@Transactional(readOnly = true)
	@Override
	public CampaignModel getOne(long id) {
		return CampaignMapper
				.entityToCampaignModel(repo.findById(id)
						.orElseThrow(() -> new BadCredentialsException("Campaign with id = " + id + " does not exist")));
	}

	@Transactional
	@Override
	public void deleteOne(long id) {
		repo.deleteById(id);
	}

	@Transactional
	@Override
	public void update(CampaignUpdate input, String token) {
		String userId = jwtUtils.getSubject(token);
		repo.update(input.campaignId(), input.campaignName(), Long.valueOf(userId));
	}

}
