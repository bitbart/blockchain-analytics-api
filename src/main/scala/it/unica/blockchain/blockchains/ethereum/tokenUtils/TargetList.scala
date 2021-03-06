package it.unica.blockchain.blockchains.ethereum.tokenUtils

import it.unica.blockchain.blockchains.ethereum.EthereumAddress

import scala.collection.mutable.ListBuffer

/** This object mantains a list of target addresses during the analisys.
  * If you want to check informations about only one or some addresses
  * into the blockchain you can add them into this object.
  */

object TargetList {
  private var listTarget: ListBuffer[String] = ListBuffer()

  def getList: ListBuffer[String] ={
    listTarget
  }

  def add(address: EthereumAddress){
    if(address != null)
      listTarget += address.address
  }

  def contains(address : EthereumAddress): Boolean ={
    if(address != null)
      getList.contains(address.address)
    else
      false
  }
}
