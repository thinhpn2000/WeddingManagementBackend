package com.wedding.service;

import java.util.List;

import com.wedding.models.Service;
import com.wedding.repository.ServiceRepository;

public class ServiceService {

	private ServiceRepository serviceRepository;

	public ServiceService() {
		serviceRepository = new ServiceRepository();
	}

	
	public void addService(Service service) {
		serviceRepository.add(service);

	}

	
	public void deleteService(int id) {
		serviceRepository.delele(id);

	}

	
	public Service getServiceById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Service> getAllService() {
		return serviceRepository.getAll();
	}

	
	public void updateService(Service service) {
		Service oldService = serviceRepository.getByIdInService(service.getServiceID());
		if(oldService != null) {
			if (oldService.getEndingDate() == null && service.getServicePrice() != oldService.getServicePrice()) {
				// not already change yet
				serviceRepository.updateEndingService(service);
			} else if (oldService.getEndingDate() != null ) {
				// changed before
				Service oldUpdatedService = serviceRepository.getByIdInUpdatedService(service.getServiceID());
				if(service.getServicePrice() != oldUpdatedService.getServicePrice())
					serviceRepository.updateEndingUpdatedService(service);
			}
			if (service.getServiceName() != oldService.getServiceName()) {
				serviceRepository.updateName(service);
			}
			
		}
	}
}
