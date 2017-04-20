/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.nova.model;

import java.io.Serializable;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.AbstractMap;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Model for Hypervisor
 *
 */
@JsonRootName("hypervisor")
public class Hypervisor implements Serializable {
/*
    public static final class CpuInfo implements Serializable{
        private String arch;
        private String model;
        private String vendor;
        //private Map<String, String> features;
        //private Map<String, String> topology;

        *//**
         * @return the arch
         *//*
        public String getArch(){
            return arch;
        }
        
        *//**
         * @return the model
         *//*
        public String getModel(){
            return model;
        }
        
        *//**
         * @return the vendor
         *//*
        public String getVendor(){
            return vendor;
        }
        
        *//**
         * @return the features
         *//*
        //public Map<String, String> getFeatures() {
        //    return features;
        //}

         *//**
         * @return the topology
         *//*
        //public Map<String, String> getTopology() {
        //    return topology;
        //}
        
        *//**
         * @param the arch
         *//*
        public void setArch(String arch){
            this.arch = arch;
        }
        
        *//**
         * @param the model
         *//*
        public void setModel(String model){
            this.model = model;
        }
        
        *//**
         * @param the vendor
         *//*
        public void setVendor(String vendor){
            this.vendor = vendor;
        }
    }*/
    public static final class Service implements Serializable{
        
        @JsonProperty("disabled_reason")
        private String disabledReason;
        
        private String host;
        
        private String id;
        
        /**
         * @return the disabledReason
         */
        public String getDisabledReason(){
            return disabledReason;
        }
        
        /**
         * @return the host
         */
        public String getHost(){
            return host;
        }
        
        /**
         * @return the id;
         */
        public String getId(){
            return id;
        }
        
        /**
         * @param the disabledReason to set
         */
        public void setDisabledReason(String disabledReason){
            this.disabledReason = disabledReason;
        }
        
        /**
         * @param the host to set
         */
        public void setHost(String host){
            this.host = host;
        }
        
        /**
         * @param the id to set
         */
        public void setId(String id){
            this.id = id;
        }
        
        @Override
        public String toString(){
            return "Service {"
                            + "id='" + id
                            + ", host='" + host
                            + ", disabledReason='" + disabledReason
                            + '}';
        }
    }
    
//    @JsonProperty("cpu_info")
//    private CpuInfo cpu_info;
    
    @JsonProperty("current_workload")
    private String currentWorkload;
        
    @JsonProperty("disk_available_least")
    private String diskAvailableLeast;
    
    @JsonProperty("free_disk_gb")
    private String freeDiskGb;
    
    @JsonProperty("free_ram_mb")
    private String freeRamMb;
    
    @JsonProperty("host_ip")
    private String hostIp;
    
    @JsonProperty("hypervisor_hostname")
    private String hypervisorHostname;
    
    @JsonProperty("hypervisor_type")
    private String hypervisorType;
    
    @JsonProperty("hypervisor_version")
    private String hypervisorVersion;
    
    private String id;
    
    @JsonProperty("local_gb")
    private String localGb;
    
    @JsonProperty("local_gb_used")
    private String localGbUsed;
    
    @JsonProperty("memory_mb")
    private String memoryMb;
    
    @JsonProperty("memory_mb_used")
    private String memoryMbUsed;
    
    @JsonProperty("running_vms")
    private String runningVms;
    
    private Service service;
    
    private String state;
    
    private String status;
    
    private String vcpus;
    
    @JsonProperty("vcpus_used")
    private String vcpusUsed;
    
    /**
     * @return the cupInfo
     */
//    public CpuInfo getCpuInfo(){
//        return cpu_info;
//    }
    
    /**
     * @return the currentWorkload
     */
    public String getCurrentWorkload(){
        return currentWorkload;
    }

    /**
     * @return the diskAvailableLeast
     */
    public String getDiskAvailableLeast(){
        return diskAvailableLeast;
    }

    /**
     * @return the freeDiskGb
     */
    public String getFreeDiskGb(){
        return freeDiskGb;
    }
  
    /**
     * @return the freeRamMb
     */
    public String getFreeRamMb(){
        return freeRamMb;
    }

    /**
     * @return the hostIp
     */
    public String getHostIp(){
        return hostIp;
    }

    /**
     * @return the hypervisorHostname
     */
    public String getHypervisorHostname(){
        return hypervisorHostname;
    }

    /**
     * @return the hypervisorType
     */
    public String getHypervisorType(){
        return hypervisorType;
    }

    /**
     * @return the hypervisorVersion
     */
    public String getHypervisorVersion(){
        return hypervisorVersion;
    }
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the localGb
     */
    public String getLocalGb(){
        return localGb;
    }

    /**
     * @return the localGbUsed
     */
    public String getLocalGbUsed(){
        return localGbUsed;
    }

    /**
     * @return the memoryMb
     */
    public String getMemoryMb(){
        return memoryMb;
    }

    /**
     * @return the memoryMbUsed
     */
    public String getMemoryMbUsed(){
        return memoryMbUsed;
    }

    /**
     * @return the runningVms
     */
    public String getRunningVms(){
        return runningVms;
    }

    /**
     * @return the service
     */
    public Service getService(){
        return service;
    }

    /**
     * @return the state
     */
    public String getState(){
        return state;
    }

    /**
     * @return the status
     */
    public String getStatus(){
        return status;
    }

    /**
     * @return the vcpus
     */
    public String getVcpus(){
        return vcpus;
    }

    /**
     * @return the vcpusUsed
     */
    public String getVcpusUsed(){
        return vcpusUsed;
    }

    @Override
    public String toString() {
        return "Hypervisor {"
        + "hypervisorHostname='" + hypervisorHostname
        + ", id='" + id
//        + ", cpuInfo='" + cpu_info
        + ", currentWorkload='" + currentWorkload
        + ", diskAvailableLeast='" + diskAvailableLeast
        + ", freeDiskGb='" + freeDiskGb
        + ", freeRamMb='" + freeRamMb
        + ", hostIp='" + hostIp
        + ", hypervisorType='" + hypervisorType
        + ", hypervisorVersion='" + hypervisorVersion
        + ", localGb='" + localGb
        + ", localGbUsed='" + localGbUsed
        + ", memoryMb='" + memoryMb
        + ", memoryMbUsed='" + memoryMbUsed
        + ", runningVms='" + runningVms
        + ", service='" + service
        + ", state='" + state
        + ", status='" + status
        + ", vcpus='" + vcpus
        + ", vcpusUsed='" + vcpusUsed
        + '}';
    }
}
