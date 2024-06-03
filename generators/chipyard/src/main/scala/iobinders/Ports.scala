package chipyard.iobinders

import chisel3._
import chisel3.experimental.{Analog}
import sifive.blocks.devices.uart.{UARTPortIO}
import sifive.blocks.devices.spi.{SPIFlashParams, SPIPortIO}
import sifive.blocks.devices.gpio.{GPIOPortIO}
import testchipip.util.{ClockedIO}
import testchipip.serdes.{TLSerdesser, SerialIO, SerialTLParams}
import testchipip.spi.{SPIChipIO}
import testchipip.cosim.{TraceOutputTop, SpikeCosimConfig}
import testchipip.iceblk.{BlockDeviceIO, BlockDeviceConfig}
import testchipip.tsi.{UARTTSIIO}
import icenet.{NICIOvonly, NICConfig}
import org.chipsalliance.cde.config.{Parameters}
import freechips.rocketchip.amba.axi4.{AXI4Bundle, AXI4EdgeParameters}
import freechips.rocketchip.subsystem.{MemoryPortParams, MasterPortParams, SlavePortParams}
import freechips.rocketchip.devices.debug.{ClockedDMIIO}
import freechips.rocketchip.tilelink.{TLBundle}
import org.chipsalliance.diplomacy.nodes.{HeterogeneousBag}

trait Port[T <: Data] {
  val getIO: () => T
  // port.io should only be called in the TestHarness context
  lazy val io = getIO()
}

trait HasChipyardPorts {
  def ports: Seq[Port[_]]
}

// These case classes are generated by IOBinders, and interpreted by HarnessBinders
case class GPIOPort        (val getIO: () => Analog, val gpioId: Int, val pinId: Int)
    extends Port[Analog]

case class GPIOPinsPort    (val getIO: () => GPIOPortIO, val gpioId: Int)
    extends Port[GPIOPortIO]

case class I2CPort         (val getIO: () => sifive.blocks.devices.i2c.I2CPort)
    extends Port[sifive.blocks.devices.i2c.I2CPort]

case class UARTPort        (val getIO: () => UARTPortIO, val uartNo: Int, val freqMHz: Int)
    extends Port[UARTPortIO]

case class SPIFlashPort    (val getIO: () => SPIChipIO, val params: SPIFlashParams, val spiId: Int)
    extends Port[SPIChipIO]

case class SPIPort         (val getIO: () => SPIPortIO)
    extends Port[SPIPortIO]

case class BlockDevicePort (val getIO: () => ClockedIO[BlockDeviceIO], val params: BlockDeviceConfig)
    extends Port[ClockedIO[BlockDeviceIO]]

case class NICPort         (val getIO: () => ClockedIO[NICIOvonly], val params: NICConfig)
    extends Port[ClockedIO[NICIOvonly]]

case class AXI4MemPort     (val getIO: () => ClockedIO[AXI4Bundle], val params: MemoryPortParams, val edge: AXI4EdgeParameters, val clockFreqMHz: Int)
    extends Port[ClockedIO[AXI4Bundle]]

case class AXI4MMIOPort    (val getIO: () => ClockedIO[AXI4Bundle], val params: MasterPortParams, val edge: AXI4EdgeParameters)
    extends Port[ClockedIO[AXI4Bundle]]

case class AXI4InPort      (val getIO: () => ClockedIO[AXI4Bundle], val params: SlavePortParams)
    extends Port[ClockedIO[AXI4Bundle]]

case class ExtIntPort      (val getIO: () => UInt)
    extends Port[UInt]

case class DMIPort         (val getIO: () => ClockedDMIIO)
    extends Port[ClockedDMIIO]

case class JTAGPort        (val getIO: () => JTAGChipIO)
    extends Port[JTAGChipIO]

// Lack of nice union types in scala-2 means we have to set this type as Data
case class SerialTLPort    (val getIO: () => Data, val params: SerialTLParams, val serdesser: TLSerdesser, val portId: Int)
    extends Port[Data]

case class ChipIdPort      (val getIO: () => UInt)
    extends Port[UInt]

case class UARTTSIPort     (val getIO: () => UARTTSIIO)
    extends Port[UARTTSIIO]

case class SuccessPort     (val getIO: () => Bool)
    extends Port[Bool]

case class TracePort       (val getIO: () => TraceOutputTop, val cosimCfg: SpikeCosimConfig)
    extends Port[TraceOutputTop]

case class CustomBootPort  (val getIO: () => Bool)
    extends Port[Bool]

case class ClockPort       (val getIO: () => Clock, val freqMHz: Double)
    extends Port[Clock]

case class ClockTapPort    (val getIO: () => Clock)
    extends Port[Clock]

case class ResetPort       (val getIO: () => AsyncReset)
    extends Port[Reset]

case class DebugResetPort  (val getIO: () => Reset)
    extends Port[Reset]

case class JTAGResetPort   (val getIO: () => Reset)
    extends Port[Reset]

case class TLMemPort       (val getIO: () => HeterogeneousBag[TLBundle])
    extends Port[HeterogeneousBag[TLBundle]]

case class OffchipSelPort  (val getIO: () => UInt)
    extends Port[UInt]

case class GCDBusyPort     (val getIO: () => Bool)
    extends Port[Bool]

